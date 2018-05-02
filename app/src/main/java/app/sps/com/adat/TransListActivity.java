package app.sps.com.adat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sps.model.Transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TransListActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_list);

        listView=(ListView)findViewById(R.id.transactionList);

        List<String> list = new ArrayList<>();


        updateData(list);
   }

    private void updateData(List<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_trans_list, R.id.transactionList, list);
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
            List<String> list = new ArrayList<>();
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
