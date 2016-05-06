package com.cs407.bookexchange.UserUI;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.adapter.SlidingMenuAdapter;
import com.cs407.bookexchange.connectors.books.SearchResultsConnector;
import com.cs407.bookexchange.db.TableDefs;
import com.cs407.bookexchange.fragment.Fragment1;
import com.cs407.bookexchange.model.ItemSlideMenu;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String search_title;
    String search_dept;
    String search_ISBN;
    Spinner staticSpinner;
    private Button searchBtn;
    TextView searchTitle;
    TextView ISBNText;

    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //KAD
        ISBNText = (TextView) findViewById(R.id.numISBN);
        searchTitle = (TextView) findViewById(R.id.searchTitle);

        staticSpinner = (Spinner)findViewById(R.id.deptSpinner);

        //KAD make spinner responsive
        // Creating adapter for spinner
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this,
                R.array.Deptnumber, android.R.layout.simple_spinner_item);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        staticSpinner.setAdapter(dataAdapter);

        staticSpinner.setOnItemSelectedListener(this);

        searchBtn =(Button)findViewById(R.id.btnSearchSearchActivity);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HashMap<String, String> params = new HashMap<String, String>();

                search_ISBN = ISBNText.getText().toString();
                search_title = searchTitle.getText().toString();
                search_dept= staticSpinner.getSelectedItem().toString();

                Log.d("SEARCHACT","searchTitle=" + search_title);

                params.put(TableDefs.Books.COLUMN_ISBN, search_ISBN);
                params.put(TableDefs.Books.COLUMN_TITLE, search_title );
                params.put(TableDefs.Books.COLUMN_DEPARTMENT, search_dept);

                SearchResultsConnector searchResultsConnector = new SearchResultsConnector(SearchActivity.this);
                searchResultsConnector.execute(params); //ResultsActivity will be called
            }
        });

        listViewSliding=(ListView)findViewById(R.id.lv_sliding_menu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        listSliding=new ArrayList<>();


        listSliding.add(new ItemSlideMenu(R.mipmap.buyicon,"Pending Requests"));
        listSliding.add(new ItemSlideMenu(R.mipmap.sellicon,"Posted Books"));
        listSliding.add(new ItemSlideMenu(R.mipmap.contacticon,"Contacts"));
        listSliding.add(new ItemSlideMenu(R.mipmap.logouticon,"Logout"));

        adapter= new SlidingMenuAdapter(this,listSliding);
        listViewSliding.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set title
        setTitle("Search");
        //item selected
        listViewSliding.setItemChecked(0, true);
        //close menu
        drawerLayout.closeDrawer(listViewSliding);


        //display fragment when start
        //replaceFragment(-1);

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listViewSliding.setItemChecked(position, true);

                //replace fragment
                replaceFragment(position);


                drawerLayout.closeDrawer(listViewSliding);
            }

        });

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.drawer_opened,R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    private void replaceFragment(int pos){

        Fragment fragment = null;
        Intent in;
        switch(pos){
            case 0:
                in = new Intent(getApplicationContext(), PendingRequestsActivity.class);
                startActivity(in);
                break;
            case 1:
                in = new Intent(getApplicationContext(), SellerManagerActivity.class);
                startActivity(in);
                break;
            case 2:
                in = new Intent(getApplicationContext(), ContactsActivity.class);
                startActivity(in);
                break;
            case 3:
                UserPrefs.doLogout();
                finish();
                in = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(in);
                break;
            default:
                fragment=new Fragment1();
                break;

        }
        if(null!=fragment){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    //KAD spinner stuff
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        search_dept = item;
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

