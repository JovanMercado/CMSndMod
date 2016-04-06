package com.modinstaller.oneshotz.soundmod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import eu.chainfire.libsuperuser.Shell;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.modinstaller.oneshotz.cm13soundmodinstaller.R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Root");

        Button rootAccess = (Button) findViewById(com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.button2);
        rootAccess.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

        if (Shell.SU.available()) {

            startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
            Toast.makeText(getApplicationContext(), "Root Access granted",
                    Toast.LENGTH_SHORT).show();


        }
                
                else {

            Toast.makeText(getApplicationContext(), "Unable to get Root Access, Please try Again",
                    Toast.LENGTH_SHORT).show();

        }
        }





        });

    }

}
