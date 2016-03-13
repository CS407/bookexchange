package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs407.bookexchange.R;

public class login extends AppCompatActivity {
Button checkButton;
    private String username; //name that user entered for login
    private String password; //password that user entered
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        checkButton =(Button)findViewById(R.id.loginButton1);
        checkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText userField= (EditText) findViewById(R.id.username);
                EditText userPass = (EditText) findViewById(R.id.password);
                username = userField.getText().toString();
                password = userPass.getText().toString();
                Toast.makeText(getBaseContext(),"Logged In as " + username,
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),search_activity.class);
                startActivity(i);
            }
        });
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
