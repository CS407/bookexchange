package com.cs407.bookexchange.UserUI;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
//import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.adapter.SlidingMenuAdapter;
import com.cs407.bookexchange.fragment.Fragment1;
import com.cs407.bookexchange.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

public class SlidingMenuActivity extends AppCompatActivity {

    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingmenu);

        listViewSliding=(ListView)findViewById(R.id.lv_sliding_menu);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        listSliding=new ArrayList<>();


        listSliding.add(new ItemSlideMenu(R.mipmap.ic_launcher,"Buy"));
        listSliding.add(new ItemSlideMenu(R.mipmap.ic_launcher,"Sell"));
        listSliding.add(new ItemSlideMenu(R.mipmap.ic_launcher,"Contacts"));
        listSliding.add(new ItemSlideMenu(R.mipmap.ic_launcher,"Logout"));

        adapter= new SlidingMenuAdapter(this,listSliding);
        listViewSliding.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set title
        setTitle(listSliding.get(0).getTitle());
        //item selected
        listViewSliding.setItemChecked(0, true);
        //close menu
        drawerLayout.closeDrawer(listViewSliding);


        //display fragment when start
        replaceFragment(-1);

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setTitle(listSliding.get(position).getTitle());
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
                    in = new Intent(getApplicationContext(), SearchActivity.class);
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
                    fragment=new Fragment1();
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

}


