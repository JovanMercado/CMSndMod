package com.modinstaller.oneshotz.soundmod;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.modinstaller.oneshotz.cm13soundmodinstaller.R;

public class PermissionsActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = getApplicationContext();
        activity = this;
        Button check_permission = (Button)findViewById(R.id.check_permission);
        Button request_permission = (Button)findViewById(R.id.request_permission);
        check_permission.setOnClickListener(this);
        request_permission.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        view = v;

        int id = v.getId();
        switch (id){
            case R.id.check_permission:
                if (checkPermission()) {

                    Snackbar.make(view,"Permission already granted.",Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(PermissionsActivity.this, SecondActivity.class));

                } else {

                    Snackbar.make(view,"Please request permission.",Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.request_permission:
                if (!checkPermission()) {

                    requestPermission();

                } else {

                    Snackbar.make(view,"Permission already granted.",Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(PermissionsActivity.this, SecondActivity.class));

                }
                break;
        }
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){

            return true;

        } else {

            return false;

        }
    }

    private void requestPermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

            Toast.makeText(context,"GPS permission allows us to access location data. Please allow in App Settings for additional functionality.",Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Snackbar.make(view,"Permission Granted, Now you can access location data.",Snackbar.LENGTH_LONG).show();
                    startActivity(new Intent(PermissionsActivity.this, SecondActivity.class));

                } else {

                    Snackbar.make(view,"Permission Denied, You cannot access location data.",Snackbar.LENGTH_LONG).show();

                }
                break;
        }
    }
}