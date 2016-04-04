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
import java.util.HashMap;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Create {

    public static String executeCreate(Constants.CRUDObject _crudobj, HashMap<String, String> params) {

        String retVal = null;

        switch(_crudobj) {
            case BOOK:retVal = addBook(params);
                break;
            case USER:retVal = addUser(params);
                break;
            case BUYER:retVal = addBuyer(params);
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

    private static String doAdd(String targetUrl, HashMap<String, String> params, String idKey, String objKey) {
        String retVal = null;

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

                connReader.readLine(); connReader.readLine();
                String response = connReader.readLine();
                JSONObject respJson = new JSONObject(response);
                String success = respJson.getString(Constants.RESPONSE_KEY_SUCCESS);
                if (success.equalsIgnoreCase("1")) {
                    String userJson = respJson.getString(objKey);
                    JSONObject obj = new JSONObject(userJson);

                    retVal = obj.getString(idKey);
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

    private static String addBook(HashMap<String, String> params) {
        return doAdd(Constants.urlCreateBook, params, TableDefs.Books.COLUMN_BOOKID, Constants.RESPONSE_KEY_BOOK);
    }

    private static String addUser(HashMap<String, String> params) {
        return doAdd(Constants.urlCreateUser, params, TableDefs.Books.COLUMN_USERID, Constants.RESPONSE_KEY_USER);
    }

    private static String addBuyer(HashMap<String, String> params) {
        String retVal = null;

        return retVal;
    }
}
