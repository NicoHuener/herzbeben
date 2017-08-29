package de.meetme;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class HelloActivity extends Activity {

    private static final String TAG = "HelloActivity";
    private static final String HOSTNAME = "<here your IP or hostname>";
    private static final int PORT = 8087;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_layout);
        Log.e(TAG, "run client");

        RestClient httpclient = new RestClient("http://" + HOSTNAME + ":" + PORT);
        try {
            Person p = httpclient.getApiService().getPerson(1);
            Log.e(TAG, p.toString());

        } catch (Exception e) {
            Log.e(TAG, "Error: " + e);
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText("Hello world!");
    }

}
