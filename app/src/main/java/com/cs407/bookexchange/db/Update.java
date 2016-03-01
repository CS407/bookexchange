package com.cs407.bookexchange.db;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Update {

    public static boolean executeUpdate(Constants.CRUDObject _crudobj, String[] params) {

        boolean retVal = false;

        switch(_crudobj) {
            case BOOK:retVal = updateBook(params);
                break;
            case USER:retVal = updateUser(params);
                break;
            case BUYER:retVal = updateBuyer(params);
                break;
            case SELLER:retVal = updateSeller(params);
                break;
        }

        return retVal;
    }

    private static boolean updateBook(String[] params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean updateUser(String[] params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean updateBuyer(String[] params) {
        boolean retVal = false;

        return retVal;
    }

    private static boolean updateSeller(String[] params) {
        boolean retVal = false;

        return retVal;
    }
}
