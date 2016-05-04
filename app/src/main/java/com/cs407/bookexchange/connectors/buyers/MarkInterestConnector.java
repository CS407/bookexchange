package com.cs407.bookexchange.connectors.buyers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.cs407.bookexchange.UserUI.ResultsActivity;
import com.cs407.bookexchange.db.Book;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Read;

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
        Log.d("[SRC]", "Search " + (retVal.booleanValue() ? "successful" : "failed"));
        Log.w("[SRC]bookResults:", bookResults.toString());

//        if(retVal.booleanValue()) { //success; we have a list of results
//TODO test separately: 1. passing array of books
        Intent resultsIntent = new Intent(context, ResultsActivity.class);
        resultsIntent.putExtra(Constants.BOOK_SEARCH_STATUS, foundBooks);
        resultsIntent.putParcelableArrayListExtra(Constants.BOOKS_RESULTS_KEY, bookResults);
        context.startActivity(resultsIntent);
//        } else {
//            //probably just have a message inside ResultsActivity if nothing returned??
//            Toast.makeText(context, "Search failed.", Toast.LENGTH_LONG).show();
//        }

        super.onPostExecute(retVal);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Boolean doInBackground(HashMap<String, String>... params) {
        ArrayList<Object> books = Read.executeRead(Constants.CRUDObject.BOOK, params[0]);

        if (books != null) {
            Log.w("[SRC] books looks like:", books.toString());
            foundBooks = true;
            for(int i = 0; i<books.size(); i++){
                bookResults.add((Book)books.get(i));
            }
            return true;
        }
        else {
            foundBooks = false;
            return false;
        }
    }
}
