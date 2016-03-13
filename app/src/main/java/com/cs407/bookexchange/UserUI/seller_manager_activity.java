package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cs407.bookexchange.R;

public class seller_manager_activity extends AppCompatActivity {
    Button addnewButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_manager_activity);

        addnewButton =(Button)findViewById(R.id.addbutton);
        addnewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), new_listing_activity.class);
                startActivity(in);
            }
        });

    }
}
