package com.cs407.bookexchange.UserUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.contacts.ReadContactsConnector;
import com.cs407.bookexchange.db.Contact;
import com.cs407.bookexchange.userprefs.Constants;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.ArrayList;

/**
 * Created by ssunny on 5/2/2016.
 */
public class ContactsActivity extends AppCompatActivity {
    private ListView contacts;
    private ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contacts = (ListView)findViewById(R.id.lvContactsContactsActivity);
        contactsAdapter = new ContactsAdapter(this, new ArrayList<Contact>());
        contacts.setAdapter(contactsAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ReadContactsConnector readContactsConnector = new ReadContactsConnector(contactsAdapter);
        readContactsConnector.execute(UserPrefs.readPreference(Constants.PREF_CUR_USER_ID));
    }
}
