package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    TextView textView;
    Button button;
    ProgressBar progressBar, progressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        progressIndicator = findViewById(R.id.progressIndicator);

        textView.setText("");
        progressIndicator.setVisibility(View.INVISIBLE);
    }

    public void uploadTask(View view) {
//        Toast.makeText(this, "button clicked...", Toast.LENGTH_SHORT).show();

//        UploadTask uploadTask = new UploadTask();
//        uploadTask.execute();

        new UploadTask().execute("This is the string passaed.");

    }

    class UploadTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute: " + Thread.currentThread().getName());

            textView.setText("uploading data...");
            progressIndicator.setVisibility(View.VISIBLE);
            button.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... strings) {

            Log.i(TAG, "doInBackground: string passed:" + strings[0]);
            Log.i(TAG, "doInBackground: Thread: " + Thread.currentThread().getName());
            for (int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }

            return "finally the task is complete";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0] + 1);
            Log.i(TAG, "onProgressUpdate: " + Thread.currentThread().getName());
        }

        @Override
        protected void onPostExecute(String string) {
            Log.i(TAG, "onPostExecute: " + Thread.currentThread().getName());

            textView.setText(string);
            progressIndicator.setVisibility(View.INVISIBLE);
            button.setEnabled(true);
        }
    }

}
