package com.cs407.bookexchange.db;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Update {

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
            Log.d("[UPDATE] post", "postData: " + postData.toString());
            return postData.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException uee) {
            Log.d("[DB] Unsup Encoding ", uee.getMessage());
            return null;
        }
    }


    public static boolean executeUpdate(Constants.CRUDObject _crudobj, HashMap<String, String> params) {

        boolean retVal = false;

        switch(_crudobj) {
            case BOOK:retVal = updateBook(params);
                break;
            case USER:retVal = updateUser(params);
                break;
            case BUYER:retVal = updateBuyer(params);
                break;
        }

        return retVal;
    }

    private static boolean updateBook(HashMap<String, String> params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean updateUser(HashMap<String, String> params) {
        boolean retVal = false;

        return retVal;
    }

    /*
     * Called when a user marks interested/uninterested in a book.
     */
    private static boolean updateBuyer(HashMap<String, String> params) {
        boolean retVal = false;
        doUpdate(params, Constants.urlUpdateBuyer, Constants.RESPONSE_KEY_BUYER);

        return retVal;
    }

    /*
     * Return true on success else false.
     */
    private static  boolean doUpdate(HashMap<String, String> params, String targetUrl, String objKey) {
        ArrayList<Object> data = new ArrayList<Object>();
        boolean retVal = false;
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

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

//            if (objKey == Constants.RESPONSE_KEY_USER ) {
//                connReader.readLine();
//                connReader.readLine();
//            } else if(targetUrl == Constants.urlReadBooksForUser || targetUrl == Constants.urlReadRequestsForUser) {
//                connReader.readLine();
//            }
                connReader.readLine();
                String response = connReader.readLine();
                Log.d("[UPDATE]", "resp:" + response);
                JSONObject respJson = new JSONObject(response);
                String success = respJson.getString(Constants.RESPONSE_KEY_SUCCESS);
                Log.d("[UPDATE]", "resp:" + success);
                if (success.equalsIgnoreCase("1")) {
                    retVal = true;
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

        return retVal;
    }




}
