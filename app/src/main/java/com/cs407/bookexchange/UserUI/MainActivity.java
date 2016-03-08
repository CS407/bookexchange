package com.cs407.bookexchange.UserUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.cs407.bookexchange.R;


public class MainActivity extends AppCompatActivity {
Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerButton =(Button)findViewById(R.id.registerButton);

    }
}
