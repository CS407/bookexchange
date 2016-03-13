package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs407.bookexchange.R;

public class registeractivity extends AppCompatActivity {
    private String username;
    private String email;
    private String password;
    private String password2;
    private int zip;
    private String name;
    private long phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);

        Button checkButton =(Button)findViewById(R.id.registerButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText userField = (EditText) findViewById(R.id.editText);
                EditText emailField = (EditText) findViewById(R.id.editText2);
                EditText passField = (EditText) findViewById(R.id.editText3);
                EditText pass2Field = (EditText) findViewById(R.id.editText4);
                EditText nameField = (EditText) findViewById(R.id.editText5);
                EditText phoneField = (EditText) findViewById(R.id.editText7);
                EditText zipField = (EditText) findViewById(R.id.editText6);
                username = userField.getText().toString();
                email = emailField.getText().toString();
                password = passField.getText().toString();
                password2 = pass2Field.getText().toString();
                name = nameField.getText().toString();
                zip = Integer.parseInt(zipField.getText().toString());
                phoneNum = Long.parseLong(phoneField.getText().toString());
                if(password.equals(password2)) {
                    Toast.makeText(getBaseContext(), "Successfully registered as " + username,
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), login.class);
                    startActivity(i);
                    finish();
                }
                else{

                    Toast.makeText(getBaseContext(), "Passwords do not match",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String getUsername(){
        return username;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword() {
        return password;
    }

}
