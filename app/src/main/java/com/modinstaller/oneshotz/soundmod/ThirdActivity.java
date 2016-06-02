package com.modinstaller.oneshotz.soundmod;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class ThirdActivity extends Activity {

        private Button startBtn;
        private ProgressDialog mProgressDialog;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(com.modinstaller.oneshotz.cm13soundmodinstaller.R.layout.activity_third);
            startBtn = (Button)findViewById(com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.button3);
            startBtn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {

                   copyAssets();

                }
            });
        }

    private void copyAssets(){

        AssetManager assetManager = getAssets();

        String[] files = null;

        try {

            files = assetManager.list("Files");

        } catch (Exception e){

            // TODO: handle exception
            Log.e("Tag",e.getMessage());
        }

        for (String fileName : files){

            System.out.println("Files=>"+ fileName);
            InputStream in=null;
            OutputStream out=null;

            try {

                in = assetManager.open("Files/"+fileName);
                out = new FileOutputStream(Environment.getDataDirectory().toString()+"/"+fileName);
                copyFiles(in,out);
                in.close();
                in =null;
                out.flush();
                out.close();
                out =null;

            }catch (Exception e){

                // TODO: handle exception

                Log.e("Tag", e.getMessage());

            }

        }
    }

    private void copyFiles (InputStream in, OutputStream out)throws  IOException{

        byte[] buffer = new byte [1024];
        int read;
        while((read=in.read(buffer))!=-1){

            out.write(buffer,0,read);
        }


    }



    }



