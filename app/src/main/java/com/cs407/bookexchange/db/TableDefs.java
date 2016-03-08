package com.cs407.bookexchange.db;

/**
 * Created by ssunny7 on 3/7/2016.
 */
public class TableDefs {
    public static final class Books {
        public static final String TABLE_NAME = "books";

        public static final String COLUMN_BOOKID = "bookid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_AUTHORS = "authors";
        public static final String COLUMN_ISBN = "isbn";
        public static final String COLUMN_CONDITION = "condition";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_COMMENTS = "comments";
        public static final String COLUMN_DEPARTMENT = "department";
        public static final String COLUMN_COURSENO = "courseno";
        public static final String COLUMN_EDITION = "edition";
    }

    public static final class Users {
        public static final String TABLE_NAME = "users";

        public static final String COLUMN_USERID = "userid";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_ZIPCODE = "zipcode";
    }

    public static final class Buyers {
        public static final String TABLE_NAME = "buyers";

        public static final String COLUMN_BUYERID = "buyerid";
        public static final String COLUMN_BOOKID = "bookid";
        public static final String COLUMN_USERID = "userid";
    }

    public static final class Sellers {
        public static final String TABLE_NAME = "sellers";

        public static final String COLUMN_SELLERID = "sellerid";
        public static final String COLUMN_BOOKID = "bookid";
        public static final String COLUMN_USERID = "userid";
    }
}
