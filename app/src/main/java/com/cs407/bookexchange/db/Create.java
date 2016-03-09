package com.cs407.bookexchange.db;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Create {

    public static boolean executeCreate(Constants.CRUDObject _crudobj, String[] params) {

        boolean retVal = false;

        switch(_crudobj) {
            case BOOK:retVal = addBook(params);
                break;
            case USER:retVal = addUser(params);
                break;
            case BUYER:retVal = addBuyer(params);
                break;
            case SELLER:retVal = addSeller(params);
                break;
        }

        return retVal;
    }

    private static boolean addBook(String[] params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean addUser(String[] params) {
        String targetUrl = Constants.urlCreateUser;
        boolean retVal = false;

        try {
            URL userGetUrl = new URL(targetUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)userGetUrl.openConnection();

            urlConnection.setDoOutput(true);
            OutputStreamWriter connWriter = new OutputStreamWriter(urlConnection.getOutputStream());

            //creating json from params goes here
            //connWriter.write();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                connWriter.close();

                BufferedReader connReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
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
            //
        } catch (IOException ioe) {
            //
        } catch (JSONException jsoe) {
            //
        }

        return retVal;
    }

    private static boolean addBuyer(String[] params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean addSeller(String[] params) {
        boolean retVal = false;

        return retVal;
    }
}
