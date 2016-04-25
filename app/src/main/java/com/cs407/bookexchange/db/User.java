package com.cs407.bookexchange.db;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ssunny7 on 2/29/2016.
 */
public class User {

    private String _userid;
    private String _name;
    private String _username;
    private String _email;
    private String _password;
    private String _phone;
    private int _zip;

    public User() {
        _userid = _name = _username = _email = _password = _phone = null;
        _zip = -1;
    }

    public String get_userid() {
        return _userid;
    }

    public void set_userid(String _userid) {
        this._userid = _userid;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public int get_zip() {
        return _zip;
    }

    public void set_zip(int _zip) {
        this._zip = _zip;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(TableDefs.Users.COLUMN_NAME + ":" + _name);
        sb.append(TableDefs.Users.COLUMN_EMAIL + ":" + _email);
        sb.append(TableDefs.Users.COLUMN_PHONE + ":" + _phone);
        sb.append(TableDefs.Users.COLUMN_ZIPCODE + ":" + _zip);
        sb.append(TableDefs.Users.COLUMN_USERNAME + ":" + _username);

        return sb.toString();
    }

    public static User JsonToObj(JSONObject json) {
        User user = new User();

        try {
            user.set_userid(json.getString(TableDefs.Users.COLUMN_USERID));
            user.set_email(json.getString(TableDefs.Users.COLUMN_EMAIL));
            user.set_zip(json.getInt(TableDefs.Users.COLUMN_ZIPCODE));
            user.set_phone(json.getString(TableDefs.Users.COLUMN_PHONE));
            user.set_username(json.getString(TableDefs.Users.COLUMN_USERNAME));
            user.set_password(json.getString(TableDefs.Users.COLUMN_PASSWORD));
            user.set_name(json.getString(TableDefs.Users.COLUMN_NAME));
        } catch (JSONException jsoe) {
            Log.d("[USER]", jsoe.getMessage());
        }

        return user;
    }
}

