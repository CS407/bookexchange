package com.cs407.bookexchange.connectors.books;

import android.os.AsyncTask;
import android.util.Log;

import com.cs407.bookexchange.db.Book;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Read;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssunny on 4/16/16.
 */
public class GetBooksForUserConnector extends AsyncTask<HashMap<String, String>, Void, ArrayList<Object>> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Object> objects) {
        if(objects == null) {
            Log.d("[GBFUC]", "An error occurred when executing the query.");
        }

        super.onPostExecute(objects);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Object> doInBackground(HashMap<String, String>... params) {
        return Read.executeRead(Constants.CRUDObject.BOOK, params[0]);
    }
}
