package com.cs407.bookexchange.connectors.books;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cs407.bookexchange.UserUI.SellerAdapter;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Delete;
import com.cs407.bookexchange.db.TableDefs;

import java.util.HashMap;

/**
 * Created by ssunny on 4/18/16.
 */
public class DeleteBookConnector extends AsyncTask<String, Void, Boolean> {
    private SellerAdapter parent;
    private int position;

    public DeleteBookConnector(SellerAdapter _parent, int _position) {
        parent = _parent;
        position = _position;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(!aBoolean.booleanValue()) {
            Log.d("[DBC]", "Book delete failed.");
        } else {
            parent.remove(parent.getItem(position));
            parent.notifyDataSetChanged();
            Toast.makeText(parent.getContext(), "Book successfully deleted!", Toast.LENGTH_LONG).show();
        }

        super.onPostExecute(aBoolean);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(TableDefs.Books.COLUMN_BOOKID, strings[0]);

        return Delete.executeDelete(Constants.CRUDObject.BOOK, params);
    }
}
