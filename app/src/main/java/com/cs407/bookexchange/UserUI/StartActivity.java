package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.userprefs.Constants;
import com.cs407.bookexchange.userprefs.UserPrefs;


public class StartActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        UserPrefs.init(getApplicationContext());
        if(UserPrefs.readPreference(Constants.PREF_CUR_USER_USERNAME) != null) {
            Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(searchIntent);
            finish();
        }

        loginButton = (Button)findViewById(R.id.btnLoginStartActivity);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intentLogin);
            }
        });

        registerButton = (Button)findViewById(R.id.btnRegisterStartActivity);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }
}
