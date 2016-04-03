package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cs407.bookexchange.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button managerButton =(Button)findViewById(R.id.managerButton);
        managerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), SellerManagerActivity.class);
                startActivity(in);
            }
        });

        Button searchButton =(Button)findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("SearchActivity", "CLicked search");

                //get search results

                Intent in = new Intent(getApplicationContext(), ResultsActivity.class);
                startActivity(in);
            }
        });

    }

}
