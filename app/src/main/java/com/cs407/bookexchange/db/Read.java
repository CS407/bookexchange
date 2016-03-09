package com.cs407.bookexchange.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.*;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Read {

    public static ArrayList<?> executeRead(Constants.CRUDObject _crudobj, String[] params) {

        ArrayList<?> readSet = new ArrayList<>();

        switch(_crudobj) {
            case BOOK:readSet = getBookSet(params);
                        break;
            case USER:readSet = getUserSet(params);
                      break;
            case BUYER:readSet = getBuyerSet(params);
                        break;
            case SELLER:readSet = getSellerSet(params);
                        break;
        }

        return readSet;
    }

    private static ArrayList<Book> getBookSet(String[] params) {
        ArrayList<Book> bookSet = null;

        return bookSet;
    }

    private static ArrayList<User> getUserSet(String[] params) {
        ArrayList<User> userSet = new ArrayList<User>();
        String targetUrl = Constants.urlReadUser;

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
                    JSONArray usersJson = respJson.getJSONArray(Constants.RESPONSE_KEY_VALS);

                    if(usersJson.length() == 1) {
                        JSONObject obj = usersJson.getJSONObject(0);

                        User user = new User();
                        user.set_name(obj.getString(TableDefs.Users.COLUMN_NAME));
                        user.set_email(obj.getString(TableDefs.Users.COLUMN_EMAIL));
                        user.set_password(obj.getString(TableDefs.Users.COLUMN_PASSWORD));
                        user.set_username(obj.getString(TableDefs.Users.COLUMN_USERNAME));
                        user.set_phone(obj.getString(TableDefs.Users.COLUMN_PHONE));
                        user.set_zip(Integer.parseInt(obj.getString(TableDefs.Users.COLUMN_ZIPCODE)));

                        userSet.add(user);
                    } else {
                        return null;
                    }
                }

                connReader.close();
            }

            urlConnection.disconnect();
        } catch (MalformedURLException mulre) {
            return null;
        } catch (IOException ioe) {
            return null;
        } catch (JSONException jsoe) {
            return null;
        }

        return userSet;
    }

    private static ArrayList<Buyer> getBuyerSet(String[] params) {
        ArrayList<Buyer> buyerSet = null;

        return buyerSet;
    }

    private static ArrayList<Seller> getSellerSet(String[] params) {
        ArrayList<Seller> sellerSet = null;

        return sellerSet;
    }
}
