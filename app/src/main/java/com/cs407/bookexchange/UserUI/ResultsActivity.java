package com.cs407.bookexchange.UserUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.db.Book;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {


    //TODO SearchActivity will pass in an ArrayList<Book>
    ArrayList<Book> books = new ArrayList<Book>(); //add books to test display




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }
}
