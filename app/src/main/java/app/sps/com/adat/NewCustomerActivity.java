package app.sps.com.adat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.sps.model.Customer;
import com.sps.model.Location;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class NewCustomerActivity extends AppCompatActivity {

    Location[] locList;
    Spinner location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        location = findViewById(R.id.location);

        Button saveButton = findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HttpRequestTask().saveCustomer();
                Intent intent = new Intent(NewCustomerActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });

        Button cancelButton = findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewCustomerActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public Customer getCustomer() {
        Customer customer = new Customer();
        customer.setFirstName(((EditText)findViewById(R.id.fName)).getText().toString());
        customer.setLastName(((EditText)findViewById(R.id.lName)).getText().toString());
        customer.setPhone(((EditText)findViewById(R.id.phone)).getText().toString());
        customer.setAddress(((EditText)findViewById(R.id.address)).getText().toString());
        String loc = ((Spinner)findViewById(R.id.location)).getSelectedItem().toString();
        customer.setLocation(loc);
        return customer;
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
                return locList;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("NewCustomerActivity", e.getMessage(), e);
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

        private void saveCustomer() {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Customer response = restTemplate.postForObject(AppUtil.saveCustomer, getCustomer(), Customer.class);
            System.out.println(response.getFirstName());
        }
    }

    private void updateData(List<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        location.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}
