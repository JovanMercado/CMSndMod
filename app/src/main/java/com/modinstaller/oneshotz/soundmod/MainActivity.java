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
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Boolean stockSoundLibs;
    public static Boolean volumeBoost;
    public static Boolean dolbyDigitalPlus;
    public static Boolean dolbyAtmos;
    public static Boolean v4a;
    public static Boolean sony;
    public static Boolean srs;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private Context context;
    private Activity activity;
    private View view;

    ArrayList<String> selection = new ArrayList<String>();

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.modinstaller.oneshotz.cm13soundmodinstaller.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("L90 SoundMod Installer");


        Button next = (Button) findViewById(com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.button);
        next.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PermissionsActivity.class));
            }
        });


    }

 public void selectItem(View view){


boolean checked = ((CheckBox) view).isChecked();
     switch (view.getId())
     {

         case com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.checkBox:

         if (checked) {
             Toast.makeText(getApplicationContext(), "Checked",
                     Toast.LENGTH_SHORT).show();
             stockSoundLibs = true;
         }
             else {

             Toast.makeText(getApplicationContext(), "Unchecked",
                     Toast.LENGTH_SHORT).show();
             stockSoundLibs = false;

         }
             break;



     }

     switch (view.getId())
     {

         case com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.checkBox2:

             if (checked) {
                 Toast.makeText(getApplicationContext(), "Checked",
                         Toast.LENGTH_SHORT).show();
                 volumeBoost = true;
             }
             else {

                 Toast.makeText(getApplicationContext(), "Unchecked",
                         Toast.LENGTH_SHORT).show();
                 volumeBoost = false;

             }
             break;



     }

     switch (view.getId())
     {

         case com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.checkBox3:

             if (checked) {
                 Toast.makeText(getApplicationContext(), "Checked",
                         Toast.LENGTH_SHORT).show();
                 dolbyDigitalPlus = true;
             }
             else {

                 Toast.makeText(getApplicationContext(), "Unchecked",
                         Toast.LENGTH_SHORT).show();
                 dolbyDigitalPlus = false;
             }
             break;



     }
     switch (view.getId())
     {

         case com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.checkBox4:

             if (checked) {
                 Toast.makeText(getApplicationContext(), "Checked",
                         Toast.LENGTH_SHORT).show();
                 dolbyAtmos = true;
             }
             else {

                 Toast.makeText(getApplicationContext(), "Unchecked",
                         Toast.LENGTH_SHORT).show();
                 dolbyAtmos = false;
             }
             break;



     }
     switch (view.getId())
     {

         case com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.checkBox5:

             if (checked) {
                 Toast.makeText(getApplicationContext(), "Checked",
                         Toast.LENGTH_SHORT).show();
                 v4a = true;
             }
             else {

                 Toast.makeText(getApplicationContext(), "Unchecked",
                         Toast.LENGTH_SHORT).show();
                 v4a = false;
             }
             break;



     }
     switch (view.getId())
     {

         case com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.checkBox6:

             if (checked) {
                 Toast.makeText(getApplicationContext(), "Checked",
                         Toast.LENGTH_SHORT).show();
                 sony = true;
             }
             else {

                 Toast.makeText(getApplicationContext(), "Unchecked",
                         Toast.LENGTH_SHORT).show();
                 sony = false;

             }
             break;



     }
     switch (view.getId())
     {

         case com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.checkBox7:

             if (checked) {
                 Toast.makeText(getApplicationContext(), "Checked",
                         Toast.LENGTH_SHORT).show();
                 srs = true;
             }
             else {

                 Toast.makeText(getApplicationContext(), "Unchecked",
                         Toast.LENGTH_SHORT).show();
                 srs = true;
             }
             break;



     }

 }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.action_settings) {
            return true;
        }



        return super.onOptionsItemSelected(item);
    }
}

