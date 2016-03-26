package com.cs407.bookexchange.db;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Constants {
    public enum CRUDObject {
        BOOK,
        USER,
        BUYER,
        SELLER
    };

    public static String urlReadBook = "";
    public static String urlReadUser = "http://pages.cs.wisc.edu/~ssunny/webserver/attempt_login.php";
    public static String urlReadBuyer = "";
    public static String urlReadSeller = "";

    public static String urlCreateBook = "";
    public static String urlCreateUser = "http://pages.cs.wisc.edu/~ssunny/webserver/create_user.php";
    public static String urlCreateBuyer = "";
    public static String urlCreateSeller = "";

    public static String urlUpdateBook = "";
    public static String urlUpdateUser = "";
    public static String urlUpdateBuyer = "";
    public static String urlUpdateSeller = "";

    public static String urlDeleteBook = "";
    public static String urlDeleteUser = "";
    public static String urlDeleteBuyer = "";
    public static String urlDeleteSeller = "";

    public static String RESPONSE_KEY_SUCCESS = "success";
    public static String RESPONSE_KEY_USER = "user";
}
