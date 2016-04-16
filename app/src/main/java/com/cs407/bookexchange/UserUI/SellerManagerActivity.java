package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.books.GetBooksForUserConnector;
import com.cs407.bookexchange.db.TableDefs;

import java.util.HashMap;

public class SellerManagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_manager);

        GetBooksForUserConnector connector = new GetBooksForUserConnector();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(TableDefs.Books.COLUMN_USERID, "48");
        connector.execute(params);

        Button newListing = (Button)findViewById(R.id.addBookSellerManagerActivity);
        newListing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), NewListingActivity.class);
                startActivity(in);
            }
        });
    }
}
