package com.cs407.bookexchange.db;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import org.json.*;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Read {

    public static ArrayList<Object> executeRead(Constants.CRUDObject _crudobj, HashMap<String, String> params) {

        ArrayList<Object> readSet = new ArrayList<>();

        switch(_crudobj) {
            case BOOK:readSet = getBookSet(params);
                        break;
            case USER:readSet = getUserSet(params);
                      break;
            case BUYER:readSet = getBuyerSet(params);
                        break;
        }

        return readSet;
    }

    private static byte[] getPostBytes(HashMap<String, String> params)
    {
        StringBuilder postData = new StringBuilder();

        try {
            for(String key : params.keySet()) {
                if (postData.length() != 0)
                    postData.append("&");

                postData.append(URLEncoder.encode(key, "UTF-8"));
                postData.append("=");
                postData.append(URLEncoder.encode(params.get(key), "UTF-8"));
            }

            return postData.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException uee) {
            Log.d("[DB] Unsup Encoding ", uee.getMessage());
            return null;
        }
    }

    private static ArrayList<Object> getBookSet(HashMap<String, String> params) {
        ArrayList<Object> bookSet = null;

        return bookSet;
    }

    private static ArrayList<Object> getUserSet(HashMap<String, String> params) {
        String targetUrl = Constants.urlReadUser;
        ArrayList<Object> users = null;

        try {
            URL userGetUrl = new URL(targetUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)userGetUrl.openConnection();

            byte[] postData = getPostBytes(params);

            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Content-Length", String.valueOf(postData.length));
            OutputStream connWriter = urlConnection.getOutputStream();
            connWriter.write(postData);
            connWriter.flush();
            connWriter.close();

            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader connReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                connReader.readLine(); connReader.readLine();
                String response = connReader.readLine();
                JSONObject respJson = new JSONObject(response);
                String success = respJson.getString(Constants.RESPONSE_KEY_SUCCESS);
                if (success.equalsIgnoreCase("1")) {
                    users = new ArrayList<Object>();
                    String userJson = respJson.getString(Constants.RESPONSE_KEY_USER);
                    JSONObject user = new JSONObject(userJson);

                    users.add(User.JsonToObj(user));
                }
                connReader.close();
            }

            urlConnection.disconnect();
        } catch (MalformedURLException mulre) {
            Log.d("[DB] Malformed URL ", mulre.getMessage());
        } catch (IOException ioe) {
            Log.d("[DB] IO ", ioe.getMessage());
        } catch (JSONException jsoe) {
            Log.d("[DB] JSON ", jsoe.getMessage());
        }

        return users;
    }

    private static ArrayList<Object> getBuyerSet(HashMap<String, String> params) {
        ArrayList<Object> buyerSet = null;

        return buyerSet;
    }
}
