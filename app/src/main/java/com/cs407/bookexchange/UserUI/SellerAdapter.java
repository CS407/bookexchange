package com.cs407.bookexchange.UserUI;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.books.DeleteBookConnector;
import com.cs407.bookexchange.connectors.buyers.ReadBuyersConnector;
import com.cs407.bookexchange.db.Book;

import java.util.ArrayList;

/**
 * Created by ssunny on 4/17/16.
 */
public class SellerAdapter extends ArrayAdapter<Book> {
    public SellerAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listelement_book, parent, false);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadBuyersConnector readBuyersConnector = new ReadBuyersConnector();
                readBuyersConnector.execute(getItem(position).get_bookid());
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(getContext()).
                setTitle("Delete Book").
                setMessage("Do you really want to remove the selected book posting?").
                setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeleteBookConnector deleteBookConnector = new DeleteBookConnector(SellerAdapter.this, position);
                        deleteBookConnector.execute(getItem(position).get_bookid());
                    }
                }).
                setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //
                    }
                }).show();

                return true;
            }
        });

        TextView title = (TextView)convertView.findViewById(R.id.sellerBookTitle);
        title.setText(book.get_title());
        TextView authors = (TextView)convertView.findViewById(R.id.sellerBookAuthors);
        authors.setText(book.get_authors());
        TextView price = (TextView)convertView.findViewById(R.id.sellerBookPrice);
        price.setText("$ " + ((Double)book.get_price()).toString());
        TextView condition = (TextView)convertView.findViewById(R.id.sellerBookCondition);
        condition.setText("Condition: " + book.get_condition().name());
        TextView deptCourse = (TextView)convertView.findViewById(R.id.sellerBookDeptCourse);
        deptCourse.setText(book.get_dept() + " " + book.get_courseno());
        TextView edition = (TextView)convertView.findViewById(R.id.sellerBookEdition);
        String append = "th";
        if(book.get_edition() == 1)
            append = "st";
        else if(book.get_edition() == 2)
            append = "nd";
        else if(book.get_edition() == 3)
            append = "rd";
        edition.setText(book.get_edition() + append + " edition");
        TextView comments = (TextView)convertView.findViewById(R.id.sellerBookComments);
        if(book.get_comments().isEmpty())
            comments.setVisibility(View.GONE);
        else
            comments.setText(book.get_comments());

        return convertView;
    }
}
