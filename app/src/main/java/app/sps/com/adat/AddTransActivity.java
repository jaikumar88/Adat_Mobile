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

import com.sps.model.Customer;
import com.sps.model.Location;
import com.sps.model.Partner;
import com.sps.model.Product;
import com.sps.model.Transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddTransActivity extends AppCompatActivity {
    Spinner location;
    Spinner customer;
    Spinner partner;
    Spinner productType;
    Location[] locList;
    Customer[] custList;
    Partner[] partnerList;
    Product[] productList;
    EditText weight;
    EditText transDate;
    Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trans);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        location = findViewById(R.id.location);
        customer = findViewById(R.id.customer);
        partner = findViewById(R.id.shop);
        productType = findViewById(R.id.productType);

        Button saveButton = findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String custName = (String) customer.getSelectedItem();
               new HttpRequestTask().saveTransaction(custName);
                Intent intent = new Intent(AddTransActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });

        Button cancelButton = findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTransActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });

        final Spinner location = findViewById(R.id.location);
        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                String loc=location.getSelectedItem().toString();
                updateCustomer(loc);
               // Log.i("Selected item : ",items);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        final EditText totalAmount = findViewById(R.id.totalAmount);
        totalAmount.setEnabled(false);
        final EditText totalDue = findViewById(R.id.totalDueAmt);
        totalDue.setEnabled(false);
        EditText totalExpenses = findViewById(R.id.totalExpenses);
        final EditText weight = findViewById(R.id.weight);
        final EditText rateAmount = findViewById(R.id.rate);
        rateAmount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(rateAmount.getText().toString() != null && !rateAmount.getText().toString().equalsIgnoreCase("")
                        && weight.getText().toString()!=null && !weight.getText().toString().equalsIgnoreCase("")) {
                    totalAmount.setText(String.valueOf(Float.parseFloat(rateAmount.getText().toString()) * Float.parseFloat(weight.getText().toString())));
                    totalDue.setText(totalAmount.getText());
                }
            }
        });

        final EditText totalExp = findViewById(R.id.totalExpenses);
        totalExp.setEnabled(false);
        final EditText noOfItem = findViewById(R.id.noOfItem);
        final EditText kharcha =  findViewById(R.id.expensePer);

        kharcha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                float totExp = 0.00f;
                if(kharcha.getText().toString()!=null && !kharcha.getText().toString().equalsIgnoreCase("")
                        && noOfItem.getText().toString()!= null && !noOfItem.getText().toString().equalsIgnoreCase(""))
                totExp = Float.parseFloat(kharcha.getText().toString()) * Float.parseFloat(noOfItem.getText().toString());
                totalExp.setText(String.valueOf(totExp));
                totalDue.setText(String.valueOf(Float.parseFloat(totalAmount.getText().toString()) - totExp));
            }
        });

        final EditText otherExp =  findViewById(R.id.otherExp);
        otherExp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                float totExp = 0;
                if(kharcha.getText().toString()!=null && !kharcha.getText().toString().equalsIgnoreCase("")
                        && noOfItem.getText().toString()!= null && !noOfItem.getText().toString().equalsIgnoreCase("")
                        && otherExp.getText().toString()!= null && !otherExp.getText().toString().equalsIgnoreCase("") )
                    totExp = Float.parseFloat(kharcha.getText().toString()) * Float.parseFloat(noOfItem.getText().toString())
                            + Float.parseFloat(otherExp.getText().toString());
                totalExp.setText(String.valueOf(totExp));
                totalDue.setText(String.valueOf(Float.parseFloat(totalAmount.getText().toString()) - totExp));
            }
        });
        transDate = (EditText) findViewById(R.id.transDate);
        setupDatePicker();

    }



    private Transaction getTransaction() {
        Transaction tran = new Transaction();
        tran.setWeight(((EditText)findViewById(R.id.weight)).getText().toString());
        tran.setRate(((EditText)findViewById(R.id.rate)).getText().toString());
        String location = ((Spinner)findViewById(R.id.location)).getSelectedItem().toString();
        String customer = ((Spinner)findViewById(R.id.customer)).getSelectedItem().toString();
        String productType = ((Spinner)findViewById(R.id.productType)).getSelectedItem().toString();
        String partner = ((Spinner)findViewById(R.id.shop)).getSelectedItem().toString();
        for(Customer cust:custList){
            if(cust != null && cust.getLocation()!= null && cust.getLocation().equalsIgnoreCase(location))
            if(customer != null &&(customer.contains(cust.getFirstName()) && customer.contains(cust.getLastName()))){
                tran.setCustId(cust.getId());
            }
        }
        tran.setPartnerId(partner);
        tran.setProductType(productType);
        String trnDate = ((EditText)findViewById(R.id.transDate)).getText().toString();
        tran.setActivityCreateDate(stringToDate(trnDate));
        tran.setQuantity(((EditText)findViewById(R.id.noOfItem)).getText().toString());
        tran.setTotalAmount(((EditText)findViewById(R.id.totalAmount)).getText().toString());
        tran.setExpPerItem(((EditText)findViewById(R.id.expensePer)).getText().toString());
        tran.setOtherExpense(((EditText)findViewById(R.id.otherExp)).getText().toString());
        tran.setDeductionPercent(((EditText)findViewById(R.id.deduction)).getText().toString());
        tran.setTotalExpense(((EditText)findViewById(R.id.totalExpenses)).getText().toString());
        tran.setDueAmount(((EditText)findViewById(R.id.totalDueAmt)).getText().toString());

        return tran;
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
                ResponseEntity<Partner[]> responseEntity2 = restTemplate.getForEntity(AppUtil.partnerListUrl, Partner[].class);
                partnerList = responseEntity2.getBody();
                ResponseEntity<Product[]> responseEntity3 = restTemplate.getForEntity(AppUtil.productListUrl, Product[].class);
                productList = responseEntity3.getBody();

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

        private void saveTransaction(String custName) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Transaction response = restTemplate.postForObject(AppUtil.saveTransactionUrl, getTransaction(), Transaction.class);
            System.out.println(response.getDueAmount());
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
        List<String> partnerlist = new ArrayList<>();
        //partnerlist.add("Jai Kumar");
        for(Partner partner: partnerList){
            partnerlist.add(partner.getFirstName()+" "+partner.getLastName());
        }
        ArrayAdapter<String> partAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, partnerlist);
        partner.setAdapter(partAdapter);
        partAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> productlist = new ArrayList<>();

        for(Product product: productList){
            productlist.add(product.getDescription());
        }
        ArrayAdapter<String> prodAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, productlist);
        productType.setAdapter(prodAdapter);

        prodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

        transDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddTransActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        transDate.setText(sdf.format(myCalendar.getTime()));
    }


    private java.sql.Date stringToDate(String date) {
        java.sql.Date dt = null;
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
             java.util.Date dtUtil = sdf1.parse(date);
            dt = new java.sql.Date(dtUtil.getTime());
        } catch (Exception e){
            e.printStackTrace();
        }
        return dt;
    }
}


