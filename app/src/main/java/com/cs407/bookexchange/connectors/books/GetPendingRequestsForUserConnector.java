package com.cs407.bookexchange.connectors.books;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.cs407.bookexchange.UserUI.PendingRequestsAdapter;
import com.cs407.bookexchange.UserUI.SellerAdapter;
import com.cs407.bookexchange.db.Book;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Read;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssunny on 4/23/16.
 */
public class GetPendingRequestsForUserConnector extends AsyncTask<HashMap<String, String>, Void, ArrayList<Object>> {
    private Context context;
    private PendingRequestsAdapter adapter;
    private ListView listView;
    private ProgressDialog progressDialog;

    public GetPendingRequestsForUserConnector(Context _context, PendingRequestsAdapter _adapter, ListView _listView) {
        context = _context;
        adapter = _adapter;
        listView = _listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Getting all books you're interested in buying...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Object> objects) {
        super.onPostExecute(objects);
        progressDialog.dismiss();

        if(objects == null) {
            Log.d("[GPRFUC]", "An error occurred when executing the query.");
        }

        ArrayList<Book> books = new ArrayList<Book>();
        for(Object obj : objects) {
            books.add((Book)obj);
        }
        adapter = new PendingRequestsAdapter(context, books);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Object> doInBackground(HashMap<String, String>... hashMaps) {
        return Read.executeRead(Constants.CRUDObject.BOOK, hashMaps[0]);
    }
}
