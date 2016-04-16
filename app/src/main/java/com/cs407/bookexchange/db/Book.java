package com.cs407.bookexchange.db;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Book implements Parcelable{

    protected Book(Parcel in) {
        _bookid = in.readString();
        _title = in.readString();
        _authors = in.readString();
        _isbn = in.readString();
        _price = in.readDouble();
        _comments = in.readString();
        _dept = in.readString();
        _courseno = in.readString();
        _edition = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_bookid);
        dest.writeString(_title);
        dest.writeString(_authors);
        dest.writeString(_isbn);
        dest.writeDouble(_price);
        dest.writeString(_comments);
        dest.writeString(_dept);
        dest.writeString(_courseno);
        dest.writeString(_edition);
    }

    public enum Condition {
        NEW,
        USED
    };

    private String _bookid;
    private String _title;
    private String _authors;
    private String _isbn;
    private Condition _condition;
    private double _price;
    private String _comments;
    private String _dept;
    private String _courseno;
    private String _edition;

    public Book() {
        _bookid = _title = _authors = _isbn = _comments = _dept = _courseno = _edition = null;
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

    public String get_edition() {
        return _edition;
    }

    public void set_edition(String _edition) {
        this._edition = _edition;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
//KAD TODO include userid???
        sb.append(TableDefs.Books.COLUMN_TITLE+ ":" + _title);
        sb.append(TableDefs.Books.COLUMN_AUTHORS + ":" + _authors);
        sb.append(TableDefs.Books.COLUMN_ISBN + ":" + _isbn);
        sb.append(TableDefs.Books.COLUMN_CONDITION + ":" + _condition);
        sb.append(TableDefs.Books.COLUMN_PRICE + ":" + _price);
        sb.append(TableDefs.Books.COLUMN_COMMENTS+ ":" + _comments);
        sb.append(TableDefs.Books.COLUMN_DEPARTMENT+ ":" + _dept);
        sb.append(TableDefs.Books.COLUMN_COURSENO + ":" + _courseno);
        sb.append(TableDefs.Books.COLUMN_EDITION + ":" + _edition);

        return sb.toString();
    }

    public static Book JsonToObj(JSONObject json) {
        Book book = new Book();

        try {
            book.set_title(json.getString(TableDefs.Books.COLUMN_TITLE));
            book.set_authors(json.getString(TableDefs.Books.COLUMN_AUTHORS));
            book.set_isbn(json.getString(TableDefs.Books.COLUMN_ISBN));
            book.set_condition(Condition.valueOf(json.getString(TableDefs.Books.COLUMN_CONDITION)));
            book.set_price(json.getDouble(TableDefs.Books.COLUMN_PRICE));
            book.set_comments(json.getString(TableDefs.Books.COLUMN_COMMENTS));
            book.set_dept(json.getString(TableDefs.Books.COLUMN_DEPARTMENT));
            book.set_courseno(json.getString(TableDefs.Books.COLUMN_COURSENO));
            book.set_edition(json.getString(TableDefs.Books.COLUMN_EDITION));
        }catch(IllegalArgumentException ex) {
            book.set_condition(Condition.USED);
        }
        catch (JSONException jsoe) {
            Log.d("[BOOK]", jsoe.getMessage());
        }

        return book;
    }

}
