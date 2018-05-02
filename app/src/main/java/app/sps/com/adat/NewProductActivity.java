package app.sps.com.adat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sps.model.Location;
import com.sps.model.Product;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class NewProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button saveButton = findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HttpRequestTask().saveLocation();
                Intent intent = new Intent(NewProductActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });

        Button cancelButton = findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewProductActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Location[]> {
        @Override
        protected Location[] doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onPostExecute(Location[] locations) {

        }

        private void saveLocation() {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Product response = restTemplate.postForObject(AppUtil.saveProduct, getProduct(), Product.class);
            System.out.println(response.getDescription());
        }
    }

    public Product getProduct() {
        
        Product product = new Product();
        product.setName(((EditText)findViewById(R.id.name)).getText().toString());
        product.setIntrestRate(((EditText)findViewById(R.id.rate)).getText().toString());
        product.setName(((EditText)findViewById(R.id.name)).getText().toString());
        product.setDescription(((EditText)findViewById(R.id.desc)).getText().toString());
        product.setNoOfDaysFree(((EditText)findViewById(R.id.noOfDays)).getText().toString());

        return product;
    }

}
