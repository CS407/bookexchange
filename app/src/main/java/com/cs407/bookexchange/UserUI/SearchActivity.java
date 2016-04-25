package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cs407.bookexchange.R;

public class SearchActivity extends AppCompatActivity {

    private Button searchBtn;
    private Button sellerManagerBtn;
    private Button pendingRequestsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        sellerManagerBtn = (Button)findViewById(R.id.btnSellerManagerSearchActivity);
        sellerManagerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(SearchActivity.this, SellerManagerActivity.class);
                startActivity(in);
            }
        });

        pendingRequestsBtn = (Button)findViewById(R.id.btnPendingRequestsSearchActivity);
        pendingRequestsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, PendingRequestsActivity.class);
                startActivity(intent);
            }
        });

    }

}
