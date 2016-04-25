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
import com.cs407.bookexchange.db.Create;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.HashMap;

/**
 * Created by ssunny7 on 3/12/2016.
 */
public class CreateUserConnector extends AsyncTask<HashMap<String, String>, Void, String> {
    private String username;
    private Context context;
    private ProgressDialog progressDialog;

    public CreateUserConnector(Context _context, String _username) {
        context = _context;
        username = _username;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Registering and logging you in...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String retVal) {
        super.onPostExecute(retVal);
        progressDialog.dismiss();

        Log.d("[CUC]", "Registration " + (retVal != null? "successful" : "failed"));

        if(retVal != null) {
            UserPrefs.writePreference(com.cs407.bookexchange.userprefs.Constants.PREF_CUR_USER_USERNAME, username);
            UserPrefs.writePreference(com.cs407.bookexchange.userprefs.Constants.PREF_CUR_USER_ID, retVal);

            Intent searchIntent = new Intent(context, SearchActivity.class);
            context.startActivity(searchIntent);

            ((Activity)context).finish();
        } else {
            Toast.makeText(context, "Username already exists, please enter a different username.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(HashMap<String, String>... params) {
       return Create.executeCreate(Constants.CRUDObject.USER, params[0]);
    }
}
