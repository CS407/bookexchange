package com.cs407.bookexchange.db;

import android.util.Log;

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
import java.util.HashMap;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Delete {

    public static boolean executeDelete(Constants.CRUDObject _crudobj, HashMap<String, String> params) {

        boolean retVal = false;

        switch(_crudobj) {
            case BOOK:retVal = deleteBook(params);
                break;
            case USER:retVal = deleteUser(params);
                break;
            case BUYER:retVal = params.containsKey(Constants.FLAG_CALLER_MAKE_TRANSACTION)?makeTransaction(params):deleteBuyer(params);
                break;
        }

        return retVal;
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

    private static boolean doDelete(String targetUrl, HashMap<String, String> params) {
        boolean retVal = false;

        try {
            URL userGetUrl = new URL(targetUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)userGetUrl.openConnection();

            params.put("submit", "true");
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

                connReader.readLine();
                String response = connReader.readLine();
                JSONObject respJson = new JSONObject(response);
                String success = respJson.getString(Constants.RESPONSE_KEY_SUCCESS);
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

    private static boolean deleteBook(HashMap<String, String> params) {
        return doDelete(Constants.urlDeleteBook, params);
    }

    private static boolean deleteUser(HashMap<String, String> params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean deleteBuyer(HashMap<String, String> params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean makeTransaction(HashMap<String, String> params) {
        params.remove(Constants.FLAG_CALLER_MAKE_TRANSACTION);
        return doDelete(Constants.urlMakeTransaction, params);
    }
}
