package com.cs407.bookexchange.connectors.books;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Create;
import com.cs407.bookexchange.db.TableDefs;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.HashMap;

/**
 * Created by ssunny7 on 4/3/2016.
 */
public class CreateBookConnector extends AsyncTask<HashMap<String, String>, Void, String> {

    private Context context;

    public CreateBookConnector(Context _context) {
        context = _context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String retVal) {
        if(retVal != null) {
            ((Activity)context).finish();
        }

        super.onPostExecute(retVal);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(HashMap<String, String>... params) {
        params[0].put(TableDefs.Books.COLUMN_USERID, UserPrefs.readPreference(com.cs407.bookexchange.userprefs.Constants.PREF_CUR_USER_ID));

        return Create.executeCreate(Constants.CRUDObject.BOOK, params[0]);
    }
}
