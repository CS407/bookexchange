package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cs407.bookexchange.R;


public class MainActivity extends AppCompatActivity {
Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton =(Button)findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            Intent in = new Intent(getApplicationContext(), login.class);
            startActivity(in);
            }
        });
    }
}
