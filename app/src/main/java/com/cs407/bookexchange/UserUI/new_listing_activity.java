package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cs407.bookexchange.R;

public class new_listing_activity extends AppCompatActivity {
    private String bookTitle;
    private String author;
    private String edition;
    private String year;
    private String condition;
    private String dept;
    private int classNum;
    private String comments;
    private Double price;
    private long ISBN;
    private Button btncreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_listing_activity);
        btncreate =(Button)findViewById(R.id.loginButton);
        btncreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), seller_manager_activity.class);
                startActivity(in);


            }
        });
    }
    public String getBookTitle(){
        return bookTitle;
    }
    public String getAuthor(){
        return author;
    }
    public String getEdition(){
        return edition;
    }
    public String getYear(){
        return year;
    }
    public String getCondition(){
        return condition;
    }
    public String getDept(){
        return dept;
    }
    public int getClassnum(){
        return classNum;
    }
    public String getComments(){
        return comments;
    }
    public Double getPrice(){
        return price;
    }
    public long getISBN(){
        return ISBN;
    }
}
