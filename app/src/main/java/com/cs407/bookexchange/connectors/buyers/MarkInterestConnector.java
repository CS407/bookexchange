package com.cs407.bookexchange.connectors.buyers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.cs407.bookexchange.UserUI.ResultsActivity;
import com.cs407.bookexchange.db.Book;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Read;
import com.cs407.bookexchange.db.Update;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Owner on 4/27/2016.
 */
public class MarkInterestConnector extends AsyncTask<HashMap<String, String>, Void, Boolean> {
    private Context context;
    ArrayList<Book> bookResults = new ArrayList<Book>();
    boolean foundBooks;


    public MarkInterestConnector(Context _context) {
        context = _context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean retVal) {
        //TODO
    Log.d("[MIC]", "retVal: " + retVal);
        super.onPostExecute(retVal);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Boolean doInBackground(HashMap<String, String>... params) {
        boolean result = Update.executeUpdate(Constants.CRUDObject.BUYER, params[0]);
        Log.d("[MIC]", "result: " + result);
            return result;
    }
}
