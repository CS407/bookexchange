package com.cs407.bookexchange.UserUI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.db.Book;

import java.util.ArrayList;

/**
 * Created by Katie Dudenas on 4/16/2016.
 */
public class BookAdapter extends ArrayAdapter<Book> {
    Context context;
    int layoutResourceId;
    ArrayList<Book> data = null;

    /**
     * Create new BookAdapter.
     * @param context - the Activity we're using this adapter in
     * @param layoutResourceId - the layout for one list item
     * @param data - the ArrayList of Books we want to display
     */
    public BookAdapter(Context context, int layoutResourceId, ArrayList<Book> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        BookHolder holder = null;

        if(row == null){
            //Make a new row
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new BookHolder();

            //Instantiate widgets
            //holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon); //KAD TODO maybe have an image, idk
            holder.txtTitle = (TextView)row.findViewById(R.id.bookTitleAndEd);
            holder.txtAuthors= (TextView)row.findViewById(R.id.bookAuthor);
            holder.txtISBN= (TextView)row.findViewById(R.id.bookISBN);
            holder.txtCondition= (TextView)row.findViewById(R.id.bookCondition);
            holder.txtPrice= (TextView)row.findViewById(R.id.bookPrice);
            holder.txtCourse= (TextView)row.findViewById(R.id.bookCourse);
            holder.txtComments= (TextView)row.findViewById(R.id.bookComments);

            row.setTag(holder);
        }
        else{
            //Re-use old row
            holder = (BookHolder)row.getTag();
        }

        //Update text fields with book data
        Book book = data.get(position);
        holder.txtTitle.setText(book.get_title() + " (" + getEdStr(book) + " ed.)");
        holder.txtAuthors.setText(book.get_authors());
        holder.txtISBN.setText(book.get_isbn());
        holder.txtCondition.setText(book.get_condition().toString());
        holder.txtPrice.setText("$" + book.get_price());
        holder.txtCourse.setText(book.get_dept() + " " + book.get_courseno());
        holder.txtComments.setText(getMiniComment(book.get_comments()));
        //holder.imgIcon.setImageResource(Book.icon);

        return row;
    }

    private String getEdStr(Book book){
        String edStr = book.get_edition();
        char edNum = edStr.charAt(edStr.length() - 1);
        String suffix;
        switch (edNum){
            case '1':  if (edStr.length() == 1){ suffix = "st"; break;}
            case '2': if (edStr.length() == 1){ suffix = "nd"; break;}
            case '3':  if (edStr.length() == 1){ suffix = "rd"; break;}
            default: suffix = "th";
                break;
        }
        return edStr + suffix;
    }

    private String getMiniComment(String comment){
        int maxLength = 50;
        if(comment.length() < maxLength) return comment;
        else return comment.substring(0, maxLength-3) + "...";
    }

    /**
     * Cache the Views used in each row so you don't have to re-instantiate each time
     */
    static class BookHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtAuthors;
        TextView txtISBN;
        TextView txtCondition;
        TextView txtPrice;
        TextView txtCourse;
        TextView txtComments;
    }
}