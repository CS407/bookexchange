package com.cs407.bookexchange.UserUI;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.adapter.SlidingMenuAdapter;
import com.cs407.bookexchange.fragment.Fragment1;
import com.cs407.bookexchange.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

public class NewListingActivity extends AppCompatActivity {
    private String bookTitle;
    private String author;
    private String edition;
    private String year;
    private String condition;
    private String dept;
    private int classNum;
    private String comments;
    private Double price;
    private long ISBN;
    private Button btncreate;


    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_listing);
        btncreate =(Button)findViewById(R.id.BTNcreatenewbook);
        btncreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), SellerManagerActivity.class);
                startActivity(in);
                EditText editTitle = (EditText)findViewById(R.id.edittitle);
                EditText editauthor = (EditText)findViewById(R.id.editauthor);
                EditText editedition = (EditText)findViewById(R.id.editEdition);
                EditText edityear = (EditText)findViewById(R.id.edityear);
                EditText editcondition = (EditText)findViewById(R.id.editcondition);
                Spinner staticSpinner = (Spinner)findViewById(R.id.editdept);
                EditText editclassnum = (EditText)findViewById(R.id.editclassnum);
                EditText editcomment = (EditText)findViewById(R.id.editcomment);
                EditText editprice = (EditText)findViewById(R.id.editprice);
                EditText editisbn = (EditText)findViewById(R.id.editISBN);
                if("".equals(editTitle.getText().toString())||
                "".equals(editauthor.getText().toString())||
                        "".equals(staticSpinner.getSelectedItem().toString())||
                "".equals(editedition.getText().toString())||
                "".equals(edityear.getText().toString())||
                "".equals(editcondition.getText().toString())||

                        "".equals(editprice.getText().toString())||
                        "".equals(editisbn.getText().toString())
                ){
                    Toast.makeText(getBaseContext(), "some fields are empty, please make sure to enter all necessary information",
                            Toast.LENGTH_SHORT).show();
                }else{
                    bookTitle = editTitle.getText().toString();
                    author=editauthor.getText().toString();
                    edition= editedition.getText().toString();
                    year=edityear.getText().toString();//int
                    condition= editcondition.getText().toString();
                    dept=staticSpinner.getSelectedItem().toString();
                    classNum = Integer.parseInt(editclassnum.getText().toString());//int
                    comments = editcomment.getText().toString();
                    price = Double.parseDouble(editprice.getText().toString());//double
                    ISBN = Long.valueOf(editisbn.getText().toString());//long

                }


            }
        });




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
        //replaceFragment(-1);

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
    public String getBookTitle(){
        return bookTitle;
    }
    public String getAuthor(){
        return author;
    }
    public String getEdition(){
        return edition;
    }
    public String getYear(){
        return year;
    }
    public String getCondition(){
        return condition;
    }
    public String getDept(){
        return dept;
    }
    public int getClassnum(){
        return classNum;
    }
    public String getComments(){
        return comments;
    }
    public Double getPrice(){
        return price;
    }
    public long getISBN(){
        return ISBN;
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


