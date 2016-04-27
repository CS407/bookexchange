package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.books.SearchResultsConnector;
import com.cs407.bookexchange.db.TableDefs;

import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {
    String search_title;
    String search_dept;
    String search_ISBN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView ISBNText = (TextView) findViewById(R.id.numISBN);
        TextView searchTitle = (TextView) findViewById(R.id.searchTitle);

        Spinner staticSpinner = (Spinner)findViewById(R.id.deptSpinner);

        search_ISBN = ISBNText.getText().toString();
        search_title = searchTitle.getText().toString();
        search_dept= staticSpinner.getSelectedItem().toString();


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
                HashMap<String, String> params = new HashMap<String, String>();

                params.put(TableDefs.Books.COLUMN_ISBN, search_ISBN);
                params.put(TableDefs.Books.COLUMN_TITLE, search_title );
                params.put(TableDefs.Books.COLUMN_DEPARTMENT, search_dept);

                SearchResultsConnector searchResultsConnector = new SearchResultsConnector(SearchActivity.this);
                searchResultsConnector.execute(params); //ResultsActivity will be called
            }
        });

    }

}
