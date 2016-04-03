package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.users.LoginUserConnector;
import com.cs407.bookexchange.db.TableDefs;

import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Button managerButton =(Button)findViewById(R.id.managerButton);
        managerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get books


//                ISBNTest = (EditText) findViewById(R.id.);
//
//                loginButton = (Button)findViewById(R.id.btnLoginLoginActivity);
//                loginButton.setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View v) {
//                        String username = userNameEditText.getText().toString();
//                        String password = passwordEditText.getText().toString();
//
//                        HashMap<String, String> params = new HashMap<String, String>();
//                        params.put(TableDefs.Users.COLUMN_USERNAME, username);
//                        params.put(TableDefs.Users.COLUMN_PASSWORD, getPasswordHash(password));

                        //LoginUserConnector loginConnector = new LoginUserConnector(LoginActivity.this, username);
                        //loginConnector.execute(params);



//                Intent in = new Intent(getApplicationContext(), ResultsActivity.class); //go to results
//                startActivity(in);
            }
        });

    }

}
