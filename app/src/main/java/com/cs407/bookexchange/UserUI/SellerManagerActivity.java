package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.books.GetBooksForUserConnector;
import com.cs407.bookexchange.db.TableDefs;
import com.cs407.bookexchange.userprefs.Constants;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.HashMap;

public class SellerManagerActivity extends AppCompatActivity {

    private Button addBook;
    private ListView sellerBookList;
    private SellerAdapter sellerAdapter;

    @Override
    protected void onResume() {
        GetBooksForUserConnector connector = new GetBooksForUserConnector(this, sellerBookList, sellerAdapter);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put(TableDefs.Books.COLUMN_USERID, UserPrefs.readPreference(Constants.PREF_CUR_USER_ID));
        connector.execute(params);

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_manager);

        sellerBookList = (ListView)findViewById(R.id.bookListSellerManagerActivity);

        addBook = (Button)findViewById(R.id.addBookSellerManagerActivity);
        addBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), NewListingActivity.class);
                startActivity(in);
            }
        });
    }
}