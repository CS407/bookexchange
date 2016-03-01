package com.cs407.bookexchange.db;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Delete {

    public static boolean executeDelete(Constants.CRUDObject _crudobj, String[] params) {

        boolean retVal = false;

        switch(_crudobj) {
            case BOOK:retVal = deleteBook(params);
                break;
            case USER:retVal = deleteUser(params);
                break;
            case BUYER:retVal = deleteBuyer(params);
                break;
            case SELLER:retVal = deleteSeller(params);
                break;
        }

        return retVal;
    }

    private static boolean deleteBook(String[] params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean deleteUser(String[] params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean deleteBuyer(String[] params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean deleteSeller(String[] params) {
        boolean retVal = false;

        return retVal;
    }
}
