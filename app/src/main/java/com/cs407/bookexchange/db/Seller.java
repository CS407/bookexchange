package com.cs407.bookexchange.db;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class Seller {

    private String _sellerid;
    private String _bookid;
    private String _userid;

    public Seller() {
        _sellerid = _bookid = _userid = null;
    }

    public String get_sellerid() {
        return _sellerid;
    }

    public void set_sellerid(String _sellerid) {
        this._sellerid = _sellerid;
    }

    public String get_bookid() {
        return _bookid;
    }

    public void set_bookid(String _bookid) {
        this._bookid = _bookid;
    }

    public String get_userid() {
        return _userid;
    }

    public void set_userid(String _userid) {
        this._userid = _userid;
    }
}
