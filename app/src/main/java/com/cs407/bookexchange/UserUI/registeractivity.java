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
import com.cs407.bookexchange.connectors.users.CreateUserConnector;
import com.cs407.bookexchange.db.TableDefs;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText userNameEditText, emailEditText, passwordEditText;
    private EditText confirmPasswordEditText, nameEditText, phoneEditText, zipcodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameEditText = (EditText) findViewById(R.id.etUserNameRegisterActivity);
        emailEditText = (EditText) findViewById(R.id.etEmailRegisterActivity);
        passwordEditText = (EditText) findViewById(R.id.etPasswordRegisterActivity);
        confirmPasswordEditText = (EditText) findViewById(R.id.etConfirmPasswordRegisterActivity);
        nameEditText = (EditText) findViewById(R.id.etNameRegisterActivity);
        phoneEditText = (EditText) findViewById(R.id.etPhoneRegisterActivity);
        zipcodeEditText = (EditText) findViewById(R.id.etZipcodeRegisterActivity);

        Button registerButton = (Button)findViewById(R.id.btnRegisterRegisterActivity);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = userNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String name = nameEditText.getText().toString();
                int zip = Integer.parseInt(zipcodeEditText.getText().toString());
                long phoneNum = Long.parseLong(phoneEditText.getText().toString());

                if (password.equals(confirmPassword)) {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put(TableDefs.Users.COLUMN_USERNAME, username);
                    params.put(TableDefs.Users.COLUMN_PASSWORD, getPasswordHash(password));
                    params.put(TableDefs.Users.COLUMN_EMAIL, email);
                    params.put(TableDefs.Users.COLUMN_ZIPCODE, String.valueOf(zip));
                    params.put(TableDefs.Users.COLUMN_NAME, name);
                    params.put(TableDefs.Users.COLUMN_PHONE, String.valueOf(phoneNum));

                    CreateUserConnector userConnector = new CreateUserConnector(username);
                    userConnector.execute(params);
                } else {
                    Toast.makeText(getBaseContext(), "Passwords do not match",
                            Toast.LENGTH_SHORT).show();
                }
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
