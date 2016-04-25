package com.cs407.bookexchange.UserUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.books.GetBooksForUserConnector;
import com.cs407.bookexchange.connectors.books.GetPendingRequestsForUserConnector;
import com.cs407.bookexchange.db.TableDefs;
import com.cs407.bookexchange.userprefs.Constants;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.HashMap;

/**
 * Created by ssunny on 4/23/16.
 */
public class PendingRequestsActivity extends AppCompatActivity {

    private ListView pendingRequestsList;
    private PendingRequestsAdapter pendingRequestsAdapter;

    @Override
    protected void onResume() {
        GetPendingRequestsForUserConnector connector = new GetPendingRequestsForUserConnector(this, pendingRequestsAdapter, pendingRequestsList);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put(TableDefs.Books.COLUMN_USERID, UserPrefs.readPreference(Constants.PREF_CUR_USER_ID));
        connector.execute(params);

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_requests);

        pendingRequestsList = (ListView)findViewById(R.id.lvRequestsPendingRequestsActivity);
    }
}
