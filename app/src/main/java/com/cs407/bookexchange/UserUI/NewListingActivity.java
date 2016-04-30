package com.cs407.bookexchange.UserUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.books.CreateBookConnector;
import com.cs407.bookexchange.db.TableDefs;

import java.util.HashMap;

public class NewListingActivity extends AppCompatActivity {

    private EditText etTitle, etComments, etISBN, etAuthors, etEdition, etDept, etPrice, etCourseNo;
    private Spinner spnCondition;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_listing);

        etTitle = (EditText) findViewById(R.id.etTitleNewListingActivity);
        etAuthors = (EditText) findViewById(R.id.etAuthorsNewListingActivity);
        etComments = (EditText) findViewById(R.id.etCommentsNewListingActivity);
        spnCondition = (Spinner) findViewById(R.id.spnConditionNewListingActivity);
        etPrice = (EditText) findViewById(R.id.etPriceNewListingActivity);
        etCourseNo = (EditText) findViewById(R.id.etClassNewListingActivity);
        etDept = (EditText) findViewById(R.id.etDepartmentNewListingActivity);
        etISBN = (EditText) findViewById(R.id.etISBNNewListingActivity);
        etEdition = (EditText) findViewById(R.id.etEditionNewListingActivity);

        btnAdd = (Button) findViewById(R.id.btnAddNewListingActivity);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String authors = etAuthors.getText().toString();
                String comments = etComments.getText().toString();
                String condition = spnCondition.getSelectedItem().toString();
                Float price = null;
                if (!etPrice.getText().toString().isEmpty())
                    price = Float.parseFloat(etPrice.getText().toString());
                String courseno = etCourseNo.getText().toString();
                String dept = etDept.getText().toString();
                String isbn = etISBN.getText().toString();
                Integer edition = null;
                if (!etEdition.getText().toString().isEmpty())
                    edition = Integer.parseInt(etEdition.getText().toString());

                if (title.isEmpty() || authors.isEmpty() || condition.isEmpty() || price == null || edition == null || dept.isEmpty() || isbn.isEmpty()) {
                    Toast.makeText(NewListingActivity.this, "Some fields are empty, please enter all required details.", Toast.LENGTH_LONG).show();
                } else {
                    HashMap<String, String> params = new HashMap<String, String>();

                    params.put(TableDefs.Books.COLUMN_TITLE, title);
                    params.put(TableDefs.Books.COLUMN_AUTHORS, authors);
                    params.put(TableDefs.Books.COLUMN_COMMENTS, comments);
                    params.put(TableDefs.Books.COLUMN_CONDITION, condition);
                    params.put(TableDefs.Books.COLUMN_COURSENO, courseno);
                    params.put(TableDefs.Books.COLUMN_DEPARTMENT, dept);
                    params.put(TableDefs.Books.COLUMN_ISBN, isbn);
                    params.put(TableDefs.Books.COLUMN_EDITION, edition.toString());
                    params.put(TableDefs.Books.COLUMN_PRICE, price.toString());

                    CreateBookConnector bookConnector = new CreateBookConnector(NewListingActivity.this);
                    bookConnector.execute(params);
                }
            }
        });
    }
}