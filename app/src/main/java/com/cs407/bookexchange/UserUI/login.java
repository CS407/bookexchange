package com.cs407.bookexchange.UserUI;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.cs407.bookexchange.R;

public class login extends AppCompatActivity {
Button checkButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        checkButton =(Button)findViewById(R.id.registerButton);
        /*checkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent i = new Intent
            }
        });*/
    }

}
