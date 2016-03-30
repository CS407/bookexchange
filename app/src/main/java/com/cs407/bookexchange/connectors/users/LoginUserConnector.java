package com.cs407.bookexchange.connectors.users;

import android.os.AsyncTask;

import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Read;
import com.cs407.bookexchange.db.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssunny7 on 3/24/2016.
 */
public class LoginUserConnector extends AsyncTask<HashMap<String, String>, Void, Boolean> {
    private String username;

    public LoginUserConnector(String _username) {
        username = _username;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean retVal) {
        super.onPostExecute(retVal);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Boolean doInBackground(HashMap<String, String>... params) {
        ArrayList<Object> users = Read.executeRead(Constants.CRUDObject.USER, params[0]);

        if(users != null && users.size() == 1)
            return true;
        else
            return false;
    }
}