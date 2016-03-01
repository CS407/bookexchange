package com.cs407.bookexchange.db;

/**
 * Created by ssuny7 on 2/29/2016.
 */
public class Buyer {

    private String _buyerid;
    private String _bookid;
    private String _userid;

    public Buyer() {
        _buyerid = _bookid = _userid = null;
    }

    public String get_buyerid() {
        return _buyerid;
    }

    public void set_buyerid(String _sellerid) {
        this._buyerid = _sellerid;
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
