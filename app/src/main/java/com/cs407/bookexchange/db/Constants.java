package com.cs407.bookexchange.db;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Constants {


    public enum CRUDObject {
        BOOK,
        USER,
        BUYER,
    };

    public static String urlReadBooksForUser = "http://pages.cs.wisc.edu/~ssunny/webserver/get_books_for_user.php";
    public static String urlReadBooksForSearch = "";
    public static String urlReadRequestsForUser = "http://pages.cs.wisc.edu/~ssunny/webserver/get_requests_for_user.php";
    public static String urlReadUser = "http://pages.cs.wisc.edu/~ssunny/webserver/attempt_login.php";
    public static String urlReadBuyer = "";

    public static String urlCreateBook = "http://pages.cs.wisc.edu/~ssunny/webserver/create_book.php";
    public static String urlCreateUser = "http://pages.cs.wisc.edu/~ssunny/webserver/create_user.php";
    public static String urlCreateBuyer = "";

    public static String urlUpdateBook = "";
    public static String urlUpdateUser = "";
    public static String urlUpdateBuyer = "";

    public static String urlDeleteBook = "";
    public static String urlDeleteUser = "";
    public static String urlDeleteBuyer = "";

    public static final String RESPONSE_KEY_SUCCESS = "success";
    public static final String RESPONSE_KEY_USER = "user";
    public static final String RESPONSE_KEY_BOOK = "books";
    public static final String FLAG_CALLER_SELLER_MANAGER = "caller_seller_manager";

    public static final String BOOK_SEARCH_STATUS = "book_search_status";
    public static final String BOOKS_RESULTS_KEY= "book_search_results";

}