package com.cs407.bookexchange.connectors.users;

import android.content.Context;
import android.os.AsyncTask;

import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Create;

import java.util.HashMap;

/**
 * Created by ssunny7 on 3/12/2016.
 */
public class CreateUserConnector extends AsyncTask<HashMap<String, String>, Void, Boolean> {
    private String username;
    private Context context;

    public CreateUserConnector(Context _context, String _username) {
        context = _context;
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
        boolean retVal = Create.executeCreate(Constants.CRUDObject.USER, params[0]);

        return new Boolean(retVal);
    }
}
