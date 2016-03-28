package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.users.LoginUserConnector;
import com.cs407.bookexchange.db.TableDefs;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

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

                HashMap<String, String> params = new HashMap<String, String>();
                params.put(TableDefs.Users.COLUMN_USERNAME, username);
                params.put(TableDefs.Users.COLUMN_PASSWORD, getPasswordHash(password));

                LoginUserConnector loginConnector = new LoginUserConnector();
                loginConnector.execute(params);
            }
        });
    }

    private String getPasswordHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            digest.update(password.getBytes("UTF-8"));
            String hash = new String(digest.digest(), "UTF-8");

            return hash;
        } catch (NoSuchAlgorithmException nsae) {
            Log.d("[REGISTER] Hash ", nsae.getMessage());
        } catch (UnsupportedEncodingException uee) {
            Log.d("REGISTER] Unsup ", uee.getMessage());
        }

        return null;
    }
}
