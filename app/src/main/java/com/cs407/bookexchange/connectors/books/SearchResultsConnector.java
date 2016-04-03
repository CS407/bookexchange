package com.cs407.bookexchange.connectors.books;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cs407.bookexchange.UserUI.ResultsActivity;
import com.cs407.bookexchange.db.Book;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Read;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Based on LoginUserConnector created by ssunny7 on 3/24/2016.
 */
public class SearchResultsConnector extends AsyncTask<HashMap<String, String>, Void, Boolean> {
    private Context context;
    ArrayList<Book> bookResults = new ArrayList<Book>();
    boolean foundBooks;


    public SearchResultsConnector(Context _context) {
        context = _context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean retVal) {
        Log.d("[SRC]", "Search " + (retVal.booleanValue()?"successful":"failed"));

//        if(retVal.booleanValue()) { //success; we have a list of results

            Intent resultsIntent = new Intent(context, ResultsActivity.class);
            resultsIntent.putExtra("foundBooks", foundBooks);
            resultsIntent.putParcelableArrayListExtra("books", bookResults);
            context.startActivity(resultsIntent);

            ((Activity)context).finish();
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

        if (books != null && books.size() == 1) {
            foundBooks = true;
            for(int i = 0; i<books.size(); i++){
                bookResults.add((Book)books.get(i));
            }

            return true;
        } else {
            foundBooks = false;
            return false;
        }
    }
}