package com.cs407.bookexchange.UserUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs407.bookexchange.R;

public class new_listing_activity extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_listing_activity);
        btncreate =(Button)findViewById(R.id.BTNcreatenewbook);
        btncreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), seller_manager_activity.class);
                startActivity(in);
                EditText editTitle = (EditText)findViewById(R.id.edittitle);
                EditText editauthor = (EditText)findViewById(R.id.editauthor);
                EditText editedition = (EditText)findViewById(R.id.editEdition);
                EditText edityear = (EditText)findViewById(R.id.edityear);
                EditText editcondition = (EditText)findViewById(R.id.editcondition);
                EditText editdept = (EditText)findViewById(R.id.editdept);
                EditText editclassnum = (EditText)findViewById(R.id.editclassnum);
                EditText editcomment = (EditText)findViewById(R.id.editcomment);
                EditText editprice = (EditText)findViewById(R.id.editprice);
                EditText editisbn = (EditText)findViewById(R.id.editISBN);
                if("".equals(editTitle.getText().toString())||
                "".equals(editauthor.getText().toString())||
                "".equals(editedition.getText().toString())||
                "".equals(edityear.getText().toString())||
                "".equals(editcondition.getText().toString())||
                        "".equals(editdept.getText().toString())||
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
                    dept=editdept.getText().toString();
                    classNum = Integer.parseInt(editclassnum.getText().toString());//int
                    comments = editcomment.getText().toString();
                    price = Double.parseDouble(editprice.getText().toString());//double
                    ISBN = Long.valueOf(editisbn.getText().toString());//long

                }


            }
        });
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
}
