package com.cs407.bookexchange.connectors.books;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.cs407.bookexchange.UserUI.SellerAdapter;
import com.cs407.bookexchange.db.Book;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Read;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssunny on 4/16/16.
 */
public class GetBooksForUserConnector extends AsyncTask<HashMap<String, String>, Void, ArrayList<Object>> {
    private Context context;
    private ListView sellerBookList;
    private SellerAdapter sellerAdapter;

    public GetBooksForUserConnector(Context _context, ListView _sellerBookList, SellerAdapter _sellerAdapter) {
        context = _context;
        sellerBookList = _sellerBookList;
        sellerAdapter = _sellerAdapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Object> objects) {
        if(objects == null) {
            Log.d("[GBFUC]", "An error occurred when executing the query.");
        }

        ArrayList<Book> books = new ArrayList<Book>();
        for(Object obj : objects) {
            books.add((Book)obj);
        }
        sellerAdapter = new SellerAdapter(context, books);
        sellerBookList.setAdapter(sellerAdapter);

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
