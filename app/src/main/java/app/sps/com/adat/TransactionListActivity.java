package app.sps.com.adat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.sps.model.Transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class TransactionListActivity extends AppCompatActivity implements
        ListAdapter.customButtonListener {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.transactionList);

        ArrayList<String> list = new ArrayList<>();
        updateData(list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }

    @Override
    public void onButtonClickListner(int position, String value) {
        Toast.makeText(TransactionListActivity.this, "Button click " + value,
                Toast.LENGTH_SHORT).show();

    }

    private void updateData(ArrayList<String> list) {
        ListAdapter adapter = new ListAdapter(this, list);
        adapter.setCustomButtonListner(this);
        listView.setAdapter(adapter);
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Transaction[]> {
        @Override
        protected Transaction[] doInBackground(Void... params) {
            try {

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                ResponseEntity<Transaction[]> responseEntity = restTemplate.getForEntity(AppUtil.transListUrl, Transaction[].class);
                Transaction[] objects = responseEntity.getBody();
                return objects;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Transaction[] trans) {
            ArrayList<String> list = new ArrayList<>();
            for(Transaction trn: trans){
                list.add(trn.getCustomer().getFirstName()+" "+trn.getCustomer().getLastName()+" "+trn.getDueAmount());
            }
            if(trans.length == 0){
                list.add("No records found for today transaction");
            }
            updateData(list);
        }

    }

}
