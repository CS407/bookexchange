package com.cs407.bookexchange.connectors.buyers;

import android.os.AsyncTask;
import android.util.Log;

import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Read;
import com.cs407.bookexchange.db.TableDefs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssunny7 on 4/27/2016.
 */
public class ReadBuyersConnector extends AsyncTask<String, Void, ArrayList<Object>> {
    @Override
    protected void onPostExecute(ArrayList<Object> objects) {
        super.onPostExecute(objects);

        if(objects == null) {
            Log.d("[RBC]", "Failed to get buyers for selected book.");
        }
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
