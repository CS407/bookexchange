package com.cs407.bookexchange.db;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Constants {


    public enum CRUDObject {
        BOOK,
        USER,
        BUYER,
        CONTACT
    };

    public static String urlReadBooksForUser = "http://pages.cs.wisc.edu/~ssunny/webserver/get_books_for_user.php";
    public static String urlReadBooksForSearch = "http://pages.cs.wisc.edu/~dudenas/get_book.php";
    public static String urlReadRequestsForUser = "http://pages.cs.wisc.edu/~ssunny/webserver/get_requests_for_user.php";
    public static String urlReadUser = "http://pages.cs.wisc.edu/~ssunny/webserver/attempt_login.php";
    public static String urlReadBuyer = "http://pages.cs.wisc.edu/~ssunny/webserver/get_buyers_for_book.php";
    public static String urlReadContact = "http://pages.cs.wisc.edu/~ssunny/webserver/get_contacts.php";

    public static String urlCreateBook = "http://pages.cs.wisc.edu/~ssunny/webserver/create_book.php";
    public static String urlCreateUser = "http://pages.cs.wisc.edu/~ssunny/webserver/create_user.php";
    public static String urlCreateBuyer = "";

    public static String urlUpdateBook = "";
    public static String urlUpdateUser = "";
    public static String urlUpdateBuyer = "http://pages.cs.wisc.edu/~dudenas/toggle_interest.php";

    public static String urlDeleteBook = "http://pages.cs.wisc.edu/~ssunny/webserver/delete_book.php";
    public static String urlDeleteUser = "";
    public static String urlDeleteBuyer = "";

    public static String urlMakeTransaction = "http://pages.cs.wisc.edu/~ssunny/webserver/make_transaction.php";

    public static final String RESPONSE_KEY_SUCCESS = "success";
    public static final String RESPONSE_KEY_USER = "user";
    public static final String RESPONSE_KEY_BOOK = "books";
    public static final String RESPONSE_KEY_BUYER = "buyers";
    public static final String RESPONSE_KEY_CONTACT = "contacts";

    public static final String FLAG_CALLER_SELLER_MANAGER = "caller_seller_manager";
    public static final String FLAG_CALLER_MAKE_TRANSACTION = "caller_make_transaction";

    public static final String BOOK_SEARCH_STATUS = "book_search_status";
    public static final String BOOKS_RESULTS_KEY= "book_search_results";

}