package com.cs407.bookexchange.db;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Book {

    public enum Condition {
        NEW,
        USED
    };

    private String _bookid;
    private String _title;
    private String _userid;
    private String _authors;
    private String _isbn;
    private Condition _condition;
    private double _price;
    private String _comments;
    private String _dept;
    private String _courseno;
    private int _edition;

    public Book() {
        _bookid = _title = _authors = _isbn = _comments = _dept = _courseno = _userid = null;
        _edition = 0;
        _price = Double.NaN;
        _condition = null;
    }

    public String get_bookid() {
        return _bookid;
    }

    public void set_bookid(String _bookid) {
        this._bookid = _bookid;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_authors() {
        return _authors;
    }

    public void set_authors(String _authors) {
        this._authors = _authors;
    }

    public String get_isbn() {
        return _isbn;
    }

    public void set_isbn(String _isbn) {
        this._isbn = _isbn;
    }

    public Condition get_condition() {
        return _condition;
    }

    public void set_condition(Condition _condition) {
        this._condition = _condition;
    }

    public double get_price() {
        return _price;
    }

    public void set_price(double _price) {
        this._price = _price;
    }

    public String get_comments() {
        return _comments;
    }

    public void set_comments(String _comments) {
        this._comments = _comments;
    }

    public String get_dept() {
        return _dept;
    }

    public void set_dept(String _dept) {
        this._dept = _dept;
    }

    public String get_courseno() {
        return _courseno;
    }

    public void set_courseno(String _courseno) {
        this._courseno = _courseno;
    }

    public int get_edition() {
        return _edition;
    }

    public void set_edition(int _edition) {
        this._edition = _edition;
    }

    public String get_userid() {
        return _userid;
    }

    public void set_userid(String _userid) {
        this._userid = _userid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(TableDefs.Books.COLUMN_BOOKID + ":" + _bookid);
        sb.append(TableDefs.Books.COLUMN_USERID + ":" + _userid);
        sb.append(TableDefs.Books.COLUMN_TITLE + ":" + _title);
        sb.append(TableDefs.Books.COLUMN_AUTHORS + ":" + _authors);
        sb.append(TableDefs.Books.COLUMN_ISBN + ":" + _isbn);
        sb.append(TableDefs.Books.COLUMN_COMMENTS + ":" + _comments);
        sb.append(TableDefs.Books.COLUMN_DEPARTMENT + ":" + _dept);
        sb.append(TableDefs.Books.COLUMN_COURSENO + ":" + _courseno);
        sb.append(TableDefs.Books.COLUMN_EDITION + ":" + _edition);
        sb.append(TableDefs.Books.COLUMN_CONDITION + ":" + _condition.name());
        sb.append(TableDefs.Books.COLUMN_PRICE + ":" + _price);

        return sb.toString();
    }

    public static Book JsonToObj(JSONObject json) {
        Book book = new Book();

        try {
            book.set_bookid(json.getString(TableDefs.Books.COLUMN_BOOKID));
            book.set_userid(json.getString(TableDefs.Books.COLUMN_USERID));
            book.set_title(json.getString(TableDefs.Books.COLUMN_TITLE));
            book.set_authors(json.getString(TableDefs.Books.COLUMN_AUTHORS));
            book.set_isbn(json.getString(TableDefs.Books.COLUMN_ISBN));
            book.set_comments(json.getString(TableDefs.Books.COLUMN_COMMENTS));
            book.set_dept(json.getString(TableDefs.Books.COLUMN_DEPARTMENT));
            book.set_courseno(json.getString(TableDefs.Books.COLUMN_COURSENO));
            book.set_edition(json.getInt(TableDefs.Books.COLUMN_EDITION));
            book.set_condition(json.getString(TableDefs.Books.COLUMN_CONDITION).equalsIgnoreCase("new")?Condition.NEW:Condition.USED);
            book.set_price(json.getDouble(TableDefs.Books.COLUMN_PRICE));
        } catch (JSONException jsoe) {
            Log.d("[BOOK]", jsoe.getMessage());
        }

        return book;
    }
}
