package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs407.bookexchange.R;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText userNameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditText = (EditText) findViewById(R.id.etUserNameLoginActivity);
        passwordEditText = (EditText) findViewById(R.id.etPasswordLoginActivity);

        loginButton = (Button)findViewById(R.id.btnLoginLoginActivity);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getBaseContext(), "Please fill in both fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Logged In as " + username,
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
