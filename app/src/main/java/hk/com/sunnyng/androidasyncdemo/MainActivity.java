package hk.com.sunnyng.androidasyncdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "AsyncTask";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
    }


    public void startAsyncTask(View v) {
        MyAsyncTask task = new MyAsyncTask();
        task.execute("One", "Two", "Three", "Four", "Five");
    }

    private class MyAsyncTask extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {
            for (String para: params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(para);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv.append("AsyncTask is starting ... \n");

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tv.append("AsyncTask completed!");

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tv.append(values[0] + "\n");
        }
    }
}