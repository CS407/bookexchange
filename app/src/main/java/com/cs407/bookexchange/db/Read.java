package com.cs407.bookexchange.db;

import java.io.IOException;
import java.io.InputStreamReader;
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

        try {
            //append to string all the params of the query
            String targetUrl = Constants.urlReadUser;
            URL userGetUrl = new URL(targetUrl);
            HttpURLConnection urlConnection = (HttpURLConnection)userGetUrl.openConnection();
            urlConnection.setDoOutput(true);

            InputStreamReader connReader = new InputStreamReader(urlConnection.getInputStream());

            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //read data and parse JSON
            } else {
                return null;
            }

            connReader.close();
            urlConnection.disconnect();
        } catch (MalformedURLException murle) {
            return null;
        } catch (IOException ioe) {
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
