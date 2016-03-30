package com.cs407.bookexchange.connectors.users;

import android.os.AsyncTask;

import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Create;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.HashMap;

/**
 * Created by ssunny7 on 3/12/2016.
 */
public class CreateUserConnector extends AsyncTask<HashMap<String, String>, Void, Boolean> {
    private String username;

    public CreateUserConnector(String _username) {
        username = _username;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean retVal) {
        if(retVal.booleanValue()) {
            UserPrefs.writePreference(com.cs407.bookexchange.userprefs.Constants.PREF_CUR_USER, username);
        }

        super.onPostExecute(retVal);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Boolean doInBackground(HashMap<String, String>... params) {
       return Create.executeCreate(Constants.CRUDObject.USER, params[0]);
    }
}
