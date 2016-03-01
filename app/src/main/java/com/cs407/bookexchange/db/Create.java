package com.cs407.bookexchange.db;

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
        boolean retVal = false;

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
