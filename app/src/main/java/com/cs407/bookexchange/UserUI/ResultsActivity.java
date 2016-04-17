package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.db.Book;
import com.cs407.bookexchange.db.Constants;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    ListView resultsView;
    ArrayList<Book> books;
    ArrayList<Book> resultBooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultsView = (ListView)findViewById(R.id.searchResultsList);
        //books = new ArrayList<Book>(); //add books to test display

        Intent searchIntent = getIntent();
        boolean foundBooks = searchIntent.getBooleanExtra(Constants.BOOK_SEARCH_STATUS, false);
        if(foundBooks){
            resultBooks = searchIntent.getParcelableArrayListExtra(Constants.BOOKS_RESULTS_KEY);
            Log.w("{RESACT}", resultBooks.toString());

            ArrayAdapter<Book> listAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, resultBooks);
            resultsView.setAdapter(listAdapter);




        }
        else{
            //TODO
        }

    }

//    public class ResultsAdapter extends ArrayAdapter{
//
//
//
//    }




}
