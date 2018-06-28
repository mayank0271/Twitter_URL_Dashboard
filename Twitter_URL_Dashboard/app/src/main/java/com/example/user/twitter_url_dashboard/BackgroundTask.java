package com.example.user.twitter_url_dashboard;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class BackgroundTask extends AsyncTask<Void, Void, String> {
    private String JSONString=null;
    @Override
    protected String doInBackground (Void...strings){
        String url = "http://28108843.ngrok.io/simple/index.php";
        OauthResult oauthResult = new OauthResult();

        try {

            URL dbUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) dbUrl.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream;
            BufferedWriter bufferedWriter;
            String data;
            outputStream = httpURLConnection.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            data = URLEncoder.encode("OAuth_Token", "UTF-8") + "=" + URLEncoder.encode(oauthResult.getOauthToken(), "UTF-8")
                    + "&" + URLEncoder.encode("OAuth_Token_Secret", "UTF-8") + "=" + URLEncoder.encode(oauthResult.getOauthTokenSecret(), "UTF-8")
                    + "&" + URLEncoder.encode("UserID", "UTF-8") + "=" + URLEncoder.encode(LoginActivity.userID, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();


            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((JSONString = bufferedReader.readLine()) != null) {
                stringBuilder.append(JSONString + "\n");
            }
            bufferedReader.close();

            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "Value Returned";
    }

    @Override
    protected void onPreExecute () {

        super.onPreExecute();
    }


    @Override
    protected void onPostExecute (String result){
        super.onPostExecute(result) ;
        ArrayList<MyUserData> arrayList = new ArrayList<MyUserData>();
        if (result != null) {
            Log.i("result", result);

            JSONObject jsonObject, jsonPart;
            JSONArray inner;
            String pboarded = null, pleft = null, bus_id = null;
            try {
                jsonObject = new JSONObject(result);
                JSONArray arr = jsonObject.getJSONArray("testData");
                for (int i = 0; i < arr.length(); i++) {
                    inner = arr.getJSONArray(i);

                    int j = 0;
                    pboarded = inner.getString(j);
                    pleft = inner.getString((j + 1));
                    bus_id = inner.getString(j + 3);

                    String []splitterString=bus_id.split("\"");
                    ArrayList<String> array = new ArrayList<String>();
                    bus_id = "";

                    for (String a : splitterString){
                        if(a.contains("https:")){
                            array.add(a);

                        }
                    }


                   arrayList.add(new MyUserData(pboarded,pleft,array));



                    // Log.i("Message",pboarded+"   "+pleft+"   "+bus_id+"\n");


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        func(arrayList);
    }

    static void func(ArrayList<MyUserData> al){

        LoginActivity.latlnglist = al;

        for(int i=0;i<LoginActivity.latlnglist.size();i = i+2){
            Log.i("ArrayList", String.valueOf(LoginActivity.latlnglist.get(i)));
        }


    }



}
