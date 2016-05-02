package com.cs407.bookexchange.connectors.buyers;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cs407.bookexchange.UserUI.BuyersDialog;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.db.Delete;

import java.util.HashMap;

/**
 * Created by ssunny7 on 5/1/2016.
 */
public class MakeTransactionConnector extends AsyncTask<HashMap<String, String>, Void, Boolean> {
    private BuyersDialog parentDialog;
    //private ProgressDialog progressDialog;

    public MakeTransactionConnector(BuyersDialog _dialog) {
        parentDialog = _dialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        /*progressDialog = new ProgressDialog(parentDialog.getContext());
        progressDialog.setTitle("Finalizing transaction...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();*/
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        //progressDialog.dismiss();
        //progressDialog = null;

        if(!aBoolean.booleanValue()) {
            Log.d("[MTC]", "Failed to create transaction");
            Toast.makeText(parentDialog.getContext(), "Failed to create transaction for selected book and user, please try again.", Toast.LENGTH_LONG).show();
        } else {
            parentDialog.dismiss();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Boolean doInBackground(HashMap<String, String>... params) {
        params[0].put(Constants.FLAG_CALLER_MAKE_TRANSACTION, "true");
        return Delete.executeDelete(Constants.CRUDObject.BUYER, params[0]);
    }
}
