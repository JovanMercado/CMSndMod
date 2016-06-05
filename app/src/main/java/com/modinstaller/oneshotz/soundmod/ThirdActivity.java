package com.modinstaller.oneshotz.soundmod;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import eu.chainfire.libsuperuser.Shell;

public class ThirdActivity extends Activity {

    //Declare Variables
    private Button startBtn;
    private ProgressDialog mProgressDialog;
    String URL1 = "http://cmsoundmod.weebly.com/uploads/4/4/0/8/44086793/dolbyatmos.zip";
    String URL2 = "http://cmsoundmod.weebly.com/uploads/4/4/0/8/44086793/dolbydigitalplus.zip";
    String URL3 = "http://cmsoundmod.weebly.com/uploads/4/4/0/8/44086793/sony.zip";
    String URL4 = "http://cmsoundmod.weebly.com/uploads/4/4/0/8/44086793/srs.zip";
    String URL5 = "http://cmsoundmod.weebly.com/uploads/4/4/0/8/44086793/stocksound.zip";
    String URL6 = "http://cmsoundmod.weebly.com/uploads/4/4/0/8/44086793/system.zip";
    String URL7 = "http://cmsoundmod.weebly.com/uploads/4/4/0/8/44086793/v4a.zip";
    String URL8 = "http://cmsoundmod.weebly.com/uploads/4/4/0/8/44086793/volumeboost.zip";
    String zipFile1 = Environment.getExternalStorageDirectory() + "/CMSoundMod/dolbyatmos.zip";
    String zipFile2 = Environment.getExternalStorageDirectory() + "/CMSoundMod/dolbydigitalplus.zip";
    String zipFile3 = Environment.getExternalStorageDirectory() + "/CMSoundMod/sony.zip";
    String zipFile4 = Environment.getExternalStorageDirectory() + "/CMSoundMod/srs.zip";
    String zipFile5 = Environment.getExternalStorageDirectory() + "/CMSoundMod/stocksound.zip";
    String zipFile6 = Environment.getExternalStorageDirectory() + "/CMSoundMod/system.zip";
    String zipFile7 = Environment.getExternalStorageDirectory() + "/CMSoundMod/v4a.zip";
    String zipFile8 = Environment.getExternalStorageDirectory() + "/CMSoundMod/volumeboost.zip";
    String unzipLocation = Environment.getExternalStorageDirectory() + "/CMSoundMod/unzipped/";


    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Make us non-modal, so that others can receive touch events.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        // ...but notify us that it happened.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        setContentView(com.modinstaller.oneshotz.cm13soundmodinstaller.R.layout.activity_third);
        startBtn = (Button) findViewById(com.modinstaller.oneshotz.cm13soundmodinstaller.R.id.button3);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                File folder = new File(Environment.getExternalStorageDirectory() + "/CMSoundMod");
                if (!folder.exists()) {
                    folder.mkdirs();

                }

                new DownloadFromURL1().execute(URL1);

            }
        });
    }

    private class DownloadFromURL1 extends AsyncTask<String, Integer, String> {
        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            mProgressDialog = new ProgressDialog(ThirdActivity.this);
            // Set your progress dialog Title
            mProgressDialog.setTitle("Download in Progress");
            // Set your progress dialog Message
            mProgressDialog.setMessage("Downloading Libraries 1/8");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            // Show progress dialog
            mProgressDialog.show();
            System.out.println("Starting download");
        }


        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();


                System.out.println("Downloading");
                URL url = new URL(f_url[0]);
                String fileUrl = url.toString();
                String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(fileUrl);
                String name = URLUtil.guessFileName(fileUrl, null, fileExtenstion);

                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root + "/CMSoundMod/" + name);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    // Publish the progress
                    publishProgress((int) (total * 100 / lengthOfFile));
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task
         **/

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update the progress dialog
            mProgressDialog.setProgress(progress[0]);
            int current_orientation = getResources().getConfiguration().orientation;
            if (current_orientation == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        }

        @Override
        protected void onPostExecute(String file_url) {
            mProgressDialog.dismiss();
            System.out.println("Downloaded");
            new DownloadFromURL2().execute(URL2);
        }

    }

    private class DownloadFromURL2 extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            mProgressDialog = new ProgressDialog(ThirdActivity.this);
            // Set your progress dialog Title
            mProgressDialog.setTitle("Download in Progress");
            // Set your progress dialog Message
            mProgressDialog.setMessage("Downloading Libraries 2/8");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            // Show progress dialog
            mProgressDialog.show();
            System.out.println("Starting download");
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();


                System.out.println("Downloading");
                URL url = new URL(f_url[0]);
                String fileUrl = url.toString();
                String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(fileUrl);
                String name = URLUtil.guessFileName(fileUrl, null, fileExtenstion);

                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root + "/CMSoundMod/" + name);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    // Publish the progress
                    publishProgress((int) (total * 100 / lengthOfFile));
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task
         **/

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update the progress dialog
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String file_url) {
            mProgressDialog.dismiss();
            System.out.println("Downloaded");
            new DownloadFromURL3().execute(URL3);
        }

    }

    private class DownloadFromURL3 extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            mProgressDialog = new ProgressDialog(ThirdActivity.this);
            // Set your progress dialog Title
            mProgressDialog.setTitle("Download in Progress");
            // Set your progress dialog Message
            mProgressDialog.setMessage("Downloading Libraries 3/8");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            // Show progress dialog
            mProgressDialog.show();
            System.out.println("Starting download");
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();


                System.out.println("Downloading");
                URL url = new URL(f_url[0]);
                String fileUrl = url.toString();
                String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(fileUrl);
                String name = URLUtil.guessFileName(fileUrl, null, fileExtenstion);

                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root + "/CMSoundMod/" + name);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    // Publish the progress
                    publishProgress((int) (total * 100 / lengthOfFile));
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task
         **/

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update the progress dialog
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String file_url) {
            mProgressDialog.dismiss();
            System.out.println("Downloaded");
            new DownloadFromURL4().execute(URL4);
        }

    }

    private class DownloadFromURL4 extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            mProgressDialog = new ProgressDialog(ThirdActivity.this);
            // Set your progress dialog Title
            mProgressDialog.setTitle("Download in Progress");
            // Set your progress dialog Message
            mProgressDialog.setMessage("Downloading Libraries 4/8");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            // Show progress dialog
            mProgressDialog.show();
            System.out.println("Starting download");
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();


                System.out.println("Downloading");
                URL url = new URL(f_url[0]);
                String fileUrl = url.toString();
                String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(fileUrl);
                String name = URLUtil.guessFileName(fileUrl, null, fileExtenstion);

                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root + "/CMSoundMod/" + name);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    // Publish the progress
                    publishProgress((int) (total * 100 / lengthOfFile));
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task
         **/

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update the progress dialog
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String file_url) {
            mProgressDialog.dismiss();
            System.out.println("Downloaded");
            new DownloadFromURL5().execute(URL5);
        }

    }

    private class DownloadFromURL5 extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            mProgressDialog = new ProgressDialog(ThirdActivity.this);
            // Set your progress dialog Title
            mProgressDialog.setTitle("Download in Progress");
            // Set your progress dialog Message
            mProgressDialog.setMessage("Downloading Libraries 5/8");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            // Show progress dialog
            mProgressDialog.show();
            System.out.println("Starting download");
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();


                System.out.println("Downloading");
                URL url = new URL(f_url[0]);
                String fileUrl = url.toString();
                String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(fileUrl);
                String name = URLUtil.guessFileName(fileUrl, null, fileExtenstion);

                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root + "/CMSoundMod/" + name);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    // Publish the progress
                    publishProgress((int) (total * 100 / lengthOfFile));
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task
         **/

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update the progress dialog
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String file_url) {
            mProgressDialog.dismiss();
            System.out.println("Downloaded");
            new DownloadFromURL6().execute(URL6);

        }

    }

    private class DownloadFromURL6 extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            mProgressDialog = new ProgressDialog(ThirdActivity.this);
            // Set your progress dialog Title
            mProgressDialog.setTitle("Download in Progress");
            // Set your progress dialog Message
            mProgressDialog.setMessage("Downloading Libraries 6/8");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            // Show progress dialog
            mProgressDialog.show();
            System.out.println("Starting download");
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();


                System.out.println("Downloading");
                URL url = new URL(f_url[0]);
                String fileUrl = url.toString();
                String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(fileUrl);
                String name = URLUtil.guessFileName(fileUrl, null, fileExtenstion);

                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root + "/CMSoundMod/" + name);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    // Publish the progress
                    publishProgress((int) (total * 100 / lengthOfFile));
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task
         **/

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update the progress dialog
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String file_url) {
            mProgressDialog.dismiss();
            System.out.println("Downloaded");
            new DownloadFromURL7().execute(URL7);
        }

    }

    private class DownloadFromURL7 extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            mProgressDialog = new ProgressDialog(ThirdActivity.this);
            // Set your progress dialog Title
            mProgressDialog.setTitle("Download in Progress");
            // Set your progress dialog Message
            mProgressDialog.setMessage("Downloading Libraries 7/8");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            // Show progress dialog
            mProgressDialog.show();
            System.out.println("Starting download");
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();


                System.out.println("Downloading");
                URL url = new URL(f_url[0]);
                String fileUrl = url.toString();
                String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(fileUrl);
                String name = URLUtil.guessFileName(fileUrl, null, fileExtenstion);

                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root + "/CMSoundMod/" + name);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    // Publish the progress
                    publishProgress((int) (total * 100 / lengthOfFile));
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task
         **/

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update the progress dialog
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String file_url) {
            mProgressDialog.dismiss();
            System.out.println("Downloaded");
            new DownloadFromURL8().execute(URL8);
        }

    }

    private class DownloadFromURL8 extends AsyncTask<String, Integer, String> {

        /**
         * Before starting background thread
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create progress dialog
            mProgressDialog = new ProgressDialog(ThirdActivity.this);
            // Set your progress dialog Title
            mProgressDialog.setTitle("Download in Progress");
            // Set your progress dialog Message
            mProgressDialog.setMessage("Downloading Libraries 8/8");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            // Show progress dialog
            mProgressDialog.show();
            System.out.println("Starting download");
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String root = Environment.getExternalStorageDirectory().toString();


                System.out.println("Downloading");
                URL url = new URL(f_url[0]);
                String fileUrl = url.toString();
                String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(fileUrl);
                String name = URLUtil.guessFileName(fileUrl, null, fileExtenstion);

                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file

                OutputStream output = new FileOutputStream(root + "/CMSoundMod/" + name);
                byte data[] = new byte[1024];

                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;

                    // writing data to file
                    // Publish the progress
                    publishProgress((int) (total * 100 / lengthOfFile));
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }


        /**
         * After completing background task
         **/

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update the progress dialog
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String file_url) {
            mProgressDialog.dismiss();
            System.out.println("Downloaded");
            Decompress d = new Decompress(zipFile1, unzipLocation);
            d.execute();
        }

    }

    public class Decompress extends AsyncTask<Void, Integer, Integer[]> {

        private String _zipFile;
        private String _location;
        private int per = 0;
        private  ProgressDialog mProgressDialog;

        public Decompress(String zipFile, String location) {
            _zipFile = zipFile;
            _location = location;
            _dirChecker("");
        }

        @Override
        protected void onPreExecute(){

            super.onPreExecute();
            // Create progress dialog
            mProgressDialog = new ProgressDialog(ThirdActivity.this);
            // Set your progress dialog Title
            mProgressDialog.setTitle("Extraction in Progress");
            // Set your progress dialog Message
            mProgressDialog.setMessage("Extracting Libraries");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(5);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            // Show progress dialog
            mProgressDialog.show();
            System.out.println("Starting download");
        }

        @Override
        protected Integer[] doInBackground(Void... params) {
            try {
                ZipFile zip = new ZipFile(_zipFile);
                FileInputStream fin = new FileInputStream(_zipFile);
                ZipInputStream zin = new ZipInputStream(fin);
                ZipEntry ze = null;
                while ((ze = zin.getNextEntry()) != null) {

                    Log.v("Decompress", "Unzipping " + ze.getName());
                    if (ze.isDirectory()) {
                        _dirChecker(ze.getName());
                    } else {
                        // Here I am doing the update of my progress bar
                        Log.v("Decompress", "more " + ze.getName());

                        per++;
                        publishProgress(per);

                        FileOutputStream fout = new FileOutputStream(_location + ze.getName());

                        streamCopy(zin, fout);

                        zin.closeEntry();
                        fout.close();
                    }
                }
                zin.close();
            } catch (Exception e) {
                Log.e("Decompress", "unzip", e);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            mProgressDialog.setProgress(per);
        }

        protected void onPostExecute(Integer... result) {
            super.onPostExecute(result);
            Log.i("UnZip", "Completed. Total size: " + result);
            mProgressDialog.dismiss();
        }

        private void _dirChecker(String dir) {
            File f = new File(_location + dir);
            if (!f.isDirectory()) {
                f.mkdirs();
            }
        }

    }

    public static void streamCopy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[32 * 1024]; // play with sizes..
        int readCount;
        while ((readCount = in.read(buffer)) != -1) {
            out.write(buffer, 0, readCount);
        }
    }

}

