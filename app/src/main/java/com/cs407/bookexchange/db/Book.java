package com.cs407.bookexchange.db;

import android.os.Parcel;
import android.os.Parcelable;

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
}
