package com.cs407.bookexchange.connectors.contacts;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.cs407.bookexchange.UserUI.ContactsAdapter;
import com.cs407.bookexchange.db.Contact;
import com.cs407.bookexchange.db.Read;
import com.cs407.bookexchange.db.TableDefs;
import com.cs407.bookexchange.userprefs.Constants;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssunny7 on 5/2/2016.
 */
public class ReadContactsConnector extends AsyncTask<String, Void, ArrayList<Object>> {
    private ContactsAdapter contactsAdapter;

    public ReadContactsConnector(ContactsAdapter _adapter) {
        contactsAdapter = _adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<Object> _contacts) {
        super.onPostExecute(_contacts);

        if(_contacts != null) {
            ArrayList<Contact> contacts = new ArrayList<Contact>();
            for(Object obj : _contacts) {
                contacts.add((Contact)obj);
            }

            contactsAdapter.resetList(contacts);
        } else {
            Log.d("[RCC]", "Failed getting notifications/contacts for current user.");
            Toast.makeText(contactsAdapter.getContext(), "Failed to get all your contacts. Please try again later.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Object> doInBackground(String... strings) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(TableDefs.Notifs.COLUMN_USERID, strings[0]);

        return Read.executeRead(com.cs407.bookexchange.db.Constants.CRUDObject.CONTACT, params);
    }
}
