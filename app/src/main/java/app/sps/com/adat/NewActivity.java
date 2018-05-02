package app.sps.com.adat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.sps.model.Activity;
import com.sps.model.Customer;
import com.sps.model.Location;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NewActivity extends AppCompatActivity {

    Spinner location;
    Spinner customer;
    Spinner activityType;
    Spinner statusType;
    Location[] locList;
    Customer[] custList;
    EditText actDate;
     Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        customer = findViewById(R.id.cust);

        activityType = findViewById(R.id.activity_type);
        String[] dataArray = getResources().getStringArray(R.array.activityTypeData);
        ArrayAdapter<String> actAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dataArray);
        activityType.setAdapter(actAdapter);

        statusType = findViewById(R.id.status);
        String[] statusArray = getResources().getStringArray(R.array.statusTypeData);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, statusArray);
        statusType.setAdapter(statusAdapter);

        Button saveButton = findViewById(R.id.btn_save);
        actDate = (EditText) findViewById(R.id.activityDate);
        setupDatePicker();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String custName = (String) customer.getSelectedItem();
                new HttpRequestTask().saveActivity(custName);
                Intent intent = new Intent(NewActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });

        Button cancelButton = findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });

        location = findViewById(R.id.location);
        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                String loc=location.getSelectedItem().toString();
                updateCustomer(loc);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });

    }

    private void setupDatePicker() {



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        actDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(NewActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "YYYY-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        actDate.setText(sdf.format(myCalendar.getTime()));
    }
    private Activity getActivity() {
        Activity activity = new Activity();
        String todayDate = actDate.getText().toString();
        activity.setActivityCreateDate(todayDate);
        String activityType = ((Spinner)findViewById(R.id.activity_type)).getSelectedItem().toString();
        activity.setActivityType(activityType);
        activity.setIntrestrate(((EditText)findViewById(R.id.rate)).getText().toString());
        activity.setMemo(((EditText)findViewById(R.id.details)).getText().toString());
        activity.setAmount(((EditText)findViewById(R.id.amount)).getText().toString());
        String statusType = ((Spinner)findViewById(R.id.status)).getSelectedItem().toString();
        activity.setStatus(statusType);
        String location = ((Spinner)findViewById(R.id.location)).getSelectedItem().toString();
        String customer = ((Spinner)findViewById(R.id.cust)).getSelectedItem().toString();

        for(Customer cust:custList){
            if(cust != null && cust.getLocation()!= null && cust.getLocation().equalsIgnoreCase(location))
                if(customer != null &&(customer.contains(cust.getFirstName()) && customer.contains(cust.getLastName()))){
                    activity.setCustId(cust.getId());
                }
        }


        return activity;
    }

    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Location[]> {
        @Override
        protected Location[] doInBackground(Void... params) {
            try {

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                ResponseEntity<Location[]> responseEntity = restTemplate.getForEntity(AppUtil.locationListUrl, Location[].class);
                locList = responseEntity.getBody();
                ResponseEntity<Customer[]> responseEntity1 = restTemplate.getForEntity(AppUtil.customerListUrl, Customer[].class);
                custList = responseEntity1.getBody();
                return locList;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Location[] locations) {
            List<String> list = new ArrayList<>();
            list.add("       ");
            for(Location loc: locations){
                list.add(loc.getLocation());
            }
            if(locations.length == 0){
                list.add("No records");
            }
            updateData(list);
        }

        private void saveActivity(String custName) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Activity response = restTemplate.postForObject(AppUtil.saveActivity, getActivity(), Activity.class);
            System.out.println(response.getActivityType());
        }
    }

    private void updateCustomer(String loc){
        List<String> list = new ArrayList<>();
        list.add("       ");
        for(Customer cust: custList){

            if(cust != null && (cust.getLocation()!=null && cust.getLocation().equalsIgnoreCase(loc))) {
                list.add(cust.getFirstName() +" "+ cust.getLastName());
            }
        }
        ArrayAdapter<String> custAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        customer.setAdapter(custAdapter);
        custAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
    private void updateData(List<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        location.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
