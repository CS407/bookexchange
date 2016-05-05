package com.cs407.bookexchange.UserUI;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.adapter.SlidingMenuAdapter;
import com.cs407.bookexchange.db.Book;
import com.cs407.bookexchange.db.Constants;
import com.cs407.bookexchange.fragment.Fragment1;
import com.cs407.bookexchange.model.ItemSlideMenu;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    ListView resultsView;
    ArrayList<Book> resultBooks;

    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        resultsView = (ListView)findViewById(R.id.searchResultsList);
        resultsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("[RESACT]","you clicked book w/ id: " + resultBooks.get(position).get_bookid());
            }
        });

        Intent searchIntent = getIntent();
        boolean foundBooks = searchIntent.getBooleanExtra(Constants.BOOK_SEARCH_STATUS, false);
        if(foundBooks){
            resultBooks = searchIntent.getParcelableArrayListExtra(Constants.BOOKS_RESULTS_KEY);
            Log.w("{RESACT}", resultBooks.toString());

            BookAdapter listAdapter = new BookAdapter(this, R.layout.results_list_item, resultBooks);
            resultsView.setAdapter(listAdapter);




        }
        else{
            //TODO
        }



        listViewSliding=(ListView)findViewById(R.id.lv_sliding_menu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        listSliding=new ArrayList<>();


        listSliding.add(new ItemSlideMenu(R.mipmap.ic_launcher,"Pending Requests"));
        listSliding.add(new ItemSlideMenu(R.mipmap.ic_launcher,"Posted Books"));
        listSliding.add(new ItemSlideMenu(R.mipmap.ic_launcher,"Contacts"));
        listSliding.add(new ItemSlideMenu(R.mipmap.ic_launcher,"Logout"));

        adapter= new SlidingMenuAdapter(this,listSliding);
        listViewSliding.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set title
        setTitle("Search Results");
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
                fragment=new Fragment1();
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

    //KAD
//    public void markInterestBtnOnClick(View v){
//    ImageButton btn = (ImageButton) v;
//    String bkgrndStr = "filled-star";
//    if(btn.getBackground().equals(this.getResources().getDrawable(R.drawable.abc_btn_rating_star_off_mtrl_alpha))){
//        bkgrndStr = "open-star";
//        //change button image
//        btn.setImageResource(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
//    }
//    else{
//        //change button image
//        btn.setImageResource(R.drawable.abc_btn_rating_star_off_mtrl_alpha);
//    }
//    Log.d("BkAdapter", "clicked a list item button! it's bkgrnd is: " + bkgrndStr);
//
//    //call update TODO
//
//    //BookHolder selectedBook = (BookHolder) v.getTag();
//    //Log.d("BkAdapter", "V tag: " + v.getParent();
//
//}


}
