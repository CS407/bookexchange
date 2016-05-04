package com.cs407.bookexchange.db;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by ssunny7 on 5/2/2016.
 */
public class Contact {
    private String _notifid;
    private String _userid;
    private String _bookTitle;
    private double _price;
    private String _email;
    private String _phone;
    private String _name;

    public String get_notifid() {
        return _notifid;
    }

    public void set_notifid(String _notifid) {
        this._notifid = _notifid;
    }

    public String get_userid() {
        return _userid;
    }

    public void set_userid(String _userid) {
        this._userid = _userid;
    }

    public String get_bookTitle() {
        return _bookTitle;
    }

    public void set_bookTitle(String _bookTitle) {
        this._bookTitle = _bookTitle;
    }

    public double get_price() {
        return _price;
    }

    public void set_price(double _price) {
        this._price = _price;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public static Contact JsonToObj(JSONObject obj) {
        Contact contact = new Contact();

        try {
            contact.set_userid(obj.getString(TableDefs.Notifs.COLUMN_USERID));
            contact.set_notifid(obj.getString(TableDefs.Notifs.COLUMN_NOTIFID));

            String notifdata = obj.getString(TableDefs.Notifs.COLUMN_NOTIFDATA);
            HashMap<String, String> notifVals = new HashMap<String, String>();
            String[] vals = notifdata.split(",");
            for(String val : vals) {
                notifVals.put(val.split(":")[0], val.split(":")[1]);
            }

            contact.set_bookTitle(notifVals.get("title"));
            contact.set_email(notifVals.get("email"));
            contact.set_phone(notifVals.get("phone"));
            contact.set_name(notifVals.get("name"));
            contact.set_price(Double.parseDouble(notifVals.get("price")));
        } catch (JSONException jsoe) {
            Log.d("[CONTACT]", jsoe.getMessage());
        }

        return contact;
    }
}
