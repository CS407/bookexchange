package com.cs407.bookexchange.connectors.buyers;

import android.os.AsyncTask;
import android.util.Log;

import com.cs407.bookexchange.UserUI.BuyersAdapter;
import com.cs407.bookexchange.UserUI.BuyersDialog;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Read;
import com.cs407.bookexchange.db.TableDefs;
import com.cs407.bookexchange.db.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssunny7 on 4/27/2016.
 */
public class ReadBuyersConnector extends AsyncTask<String, Void, ArrayList<Object>> {
    private BuyersAdapter buyersAdapter;

    public ReadBuyersConnector(BuyersAdapter _adapter) {
        buyersAdapter = _adapter;
    }

    @Override
    protected void onPostExecute(ArrayList<Object> objects) {
        super.onPostExecute(objects);

        if(objects == null) {
            Log.d("[RBC]", "Failed to get buyers for selected book.");
        }

        ArrayList<User> buyers = new ArrayList<User>();
        for(Object buyer:  objects)
            buyers.add((User)buyer);
        buyersAdapter.resetList(buyers);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Object> doInBackground(String... strings) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(TableDefs.Buyers.COLUMN_BOOKID, strings[0]);

        return Read.executeRead(Constants.CRUDObject.BUYER, params);
    }
}
