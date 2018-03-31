package jandroid_toolkit.android.jeffsaa.exampleapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

// Import JAndroid Toolkit to class
import jandroid.android.jeffsaa.jandroid_toolkit.Utils.*;
import jandroid.android.jeffsaa.jandroid_toolkit.Models.*;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getCanonicalName();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate ProgressDialog
        progressDialog = DialogIt.getLoader(this, getString(R.string.app_name));

        getExample();
        preferencesExample();
    }

    private void getExample(){
        progressDialog.show();          // Show ProgressDialog

        Rest.get("posts", new RequestParameters(), new RestHandler() {      // Start Get Request
            @Override
            public void onSuccess(int statusCode, RestHeader[] headers, byte[] response) {

                // Show response in a Toast
                DialogIt.toastIt(getApplicationContext(), new String(response), DialogIt.LENGTH_SHORT);

                progressDialog.hide();  // Hide ProgressDialog
            }

            @Override
            public void onFailure(int statusCode, RestHeader[] headers, byte[] errorResponse, Throwable e) {

                // Show error in a Toast
                DialogIt.toastIt(getApplicationContext(), new String(errorResponse), DialogIt.LENGTH_SHORT);

                progressDialog.hide();  // Hide ProgressDialog
            }
        });
    }

    private void preferencesExample(){
        // Save value in SharePreferences
        PreferManager.set(this, "preferences_example", "JAndroid Toolkit");

        // Get and show this value
        String value = PreferManager.get(this, "preferences_example", "");
        Log.i(TAG, value);

        // Delete it
        PreferManager.del(this, "preferences_example");

    }

}