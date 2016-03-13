package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cs407.bookexchange.R;

public class new_listing_activity extends AppCompatActivity {
    private EditText edittitle;
    private EditText date_editText;
    private EditText time_editText;
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
}
