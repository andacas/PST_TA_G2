package com.example.amst2;

import android.media.Image;
import android.os.AsyncTask;

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
import java.net.URLEncoder;

public class AsyncImageQuery extends AsyncTask<String[],Void,String[]> {
    protected String[] doInBackground(String[]... datos) {
        String[] totalResultadoSQL = null;
        String type = datos[0][0];
        String login_url = datos[0][1];

        if(type.equals("call")){
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);

                InputStream iStr = httpURLConnection.getInputStream();
                BufferedReader bR = new BufferedReader(new InputStreamReader(iStr,"UTF-8"));
                String resultado="";
                String line="";

                while((line = bR.readLine()) != null){
                    resultado += line ;
                }
                bR.close();
                iStr.close();
                httpURLConnection.disconnect();

                totalResultadoSQL = new String[]{
                        resultado
                };

            } catch (MalformedURLException e ) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return totalResultadoSQL;
    }

}
