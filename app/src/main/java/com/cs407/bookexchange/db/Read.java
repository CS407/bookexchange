package com.cs407.bookexchange.db;

import java.util.ArrayList;

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
        ArrayList<User> userSet = null;

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
