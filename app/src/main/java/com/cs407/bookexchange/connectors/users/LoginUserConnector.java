package com.cs407.bookexchange.connectors.users;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cs407.bookexchange.UserUI.SearchActivity;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Read;
import com.cs407.bookexchange.db.User;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssunny7 on 3/24/2016.
 */
public class LoginUserConnector extends AsyncTask<HashMap<String, String>, Void, Boolean> {
    private String username;
    private Context context;
    private ProgressDialog progressDialog;

    public LoginUserConnector(Context _context, String _username) {
        context = _context;
        username = _username;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Logging you in...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Boolean retVal) {
        super.onPostExecute(retVal);
        progressDialog.dismiss();

        Log.d("[LUC]", "Login " + (retVal.booleanValue()?"successful":"failed"));

        if(retVal.booleanValue()) {
            Intent searchIntent = new Intent(context, SearchActivity.class);
            context.startActivity(searchIntent);

            ((Activity)context).finish();
        } else {
            Toast.makeText(context, "Login failed. Incorrect username/password.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Boolean doInBackground(HashMap<String, String>... params) {
        ArrayList<Object> users = Read.executeRead(Constants.CRUDObject.USER, params[0]);

        if(users != null && users.size() == 1) {
            User curUser = (User)users.get(0);
            UserPrefs.writePreference(com.cs407.bookexchange.userprefs.Constants.PREF_CUR_USER_ID, curUser.get_userid());
            UserPrefs.writePreference(com.cs407.bookexchange.userprefs.Constants.PREF_CUR_USER_EMAIL,  curUser.get_email());
            UserPrefs.writePreference(com.cs407.bookexchange.userprefs.Constants.PREF_CUR_USER_PHONE,  curUser.get_phone());
            UserPrefs.writePreference(com.cs407.bookexchange.userprefs.Constants.PREF_CUR_USER_USERNAME, username);

            return true;
        }
        else
            return false;
    }
}