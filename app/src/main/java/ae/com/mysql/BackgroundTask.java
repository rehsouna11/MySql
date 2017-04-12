package ae.com.mysql;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

//----------------------------------------------------------------------------

// custom class inherits abstract class AsyncTask
//AsyncTask used to create a thread
public class BackgroundTask extends AsyncTask<String,String,String> {

    //declare ctx to save context and an alertDialog to view result
    Context ctx;
    AlertDialog alertDialog;

    //----------------------------------------------------------------------------

    //Constructor that receive context from MainActivity
    //assign the context to ctx
    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }


    //----------------------------------------------------------------------------

    //a method which is called before execution
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //Build alertDialog using builder
        //sending context as parameter
        //then calling create function to create dialog
        //setting title for the dialog
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login information...");
    }

    //----------------------------------------------------------------------------


    @Override
    //execution in background
    protected String doInBackground(String... params) {


        //the url
        String reg_url = "http://192.168.0.111/webapp/register.php";
        String login_url = "http://192.168.0.111/webapp/login.php";

        String method = params[0];

        if(method.equals("register")){
            String name = params[1];
            String user_name = params[2];
            String user_pass = params[3];

            try {
                //create new URL instance and give it a string url
                URL url = new URL(reg_url);
                URLConnection urlConnection = url.openConnection();

                //http connection
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                //outputstream
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("user","UTF-8") + "=" + URLEncoder.encode(name,"UTF-8")+ "&" +
                            URLEncoder.encode("user_name","UTF-8") + "=" + URLEncoder.encode(user_name,"UTF-8")+ "&" +
                        URLEncoder.encode("user_pass","UTF-8") + "=" + URLEncoder.encode(user_pass,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                return "Registration Success...";

            }

            catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        //----------------------------------------------------------------------------

        else if (method.equals("login")){
            String login_name = params[1];
            String login_pass = params[2];

            try {

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data = URLEncoder.encode("login_name","UTF-8") + "=" +  URLEncoder.encode(login_name,"UTF-8") + "&" +
                        URLEncoder.encode("login_pass","UTF-8") + "=" +  URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "" ;
                String line = "";
                while ( (line = bufferedReader.readLine()) != null){
                    response+=line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            }

            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return null;
    }


    //----------------------------------------------------------------------------


//    @Override
//    protected void onProgressUpdate(String... values) {
//        super.onProgressUpdate(values);
//    }


    //----------------------------------------------------------------------------


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result.equals("Registration Success...")){
        Toast.makeText(ctx,result, Toast.LENGTH_SHORT).show();
    }
        else {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
}}
