package com.cs407.bookexchange.UserUI;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.books.SearchResultsConnector;
import com.cs407.bookexchange.connectors.buyers.MarkInterestConnector;
import com.cs407.bookexchange.db.Book;
import com.cs407.bookexchange.db.TableDefs;
import com.cs407.bookexchange.userprefs.Constants;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.ArrayList;
import java.util.HashMap;

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

    private void setupButton(Button btn){
        //TODO set INITIAL image: 'check' if the user is already marked interested in this book, else 'plus'

        //set on-click listener
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                //toggle pic
                if(btn.getText().equals("+")){
                    btn.setText("\u2713");
                }
                else btn.setText("+");

                //call update TODO
                View row = (View) (v.getParent());
                Log.d("BkAdapter", "V parent: " + row + " " + row.getId());
                BookHolder selectedBook = (BookHolder) row.getTag();
                String userid = UserPrefs.readPreference(Constants.PREF_CUR_USER_ID);
                String bookid = selectedBook.bookId;
                Log.d("BkAdapter", "Call update with userid: " + userid + "and bookid: " + bookid);

                HashMap<String, String> params = new HashMap<String, String>();

                params.put("userid", userid);
                params.put("bookid", bookid);

                MarkInterestConnector markInterestConnector= new MarkInterestConnector(context);
                markInterestConnector.execute(params);

            }
        });
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
            //holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon); //KAD maybe have an image, idk
            holder.txtTitle = (TextView)row.findViewById(R.id.bookTitleAndEd);
            holder.txtAuthors= (TextView)row.findViewById(R.id.bookAuthor);
            holder.txtISBN= (TextView)row.findViewById(R.id.bookISBN);
            holder.txtCondition= (TextView)row.findViewById(R.id.bookCondition);
            holder.txtPrice= (TextView)row.findViewById(R.id.bookPrice);
            holder.txtCourse= (TextView)row.findViewById(R.id.bookCourse);
            holder.txtComments= (TextView)row.findViewById(R.id.bookComments);

            holder.interestBtn= (Button)row.findViewById(R.id.interestButton);
            setupButton(holder.interestBtn);

            row.setTag(holder);
        }
        else{
            //Re-use old row
            holder = (BookHolder)row.getTag();
        }

        //Update text fields with book data
        Book book = data.get(position);
        holder.bookId = book.get_bookid();
        holder.txtTitle.setText(book.get_title() + " (" + getEdStr(book) + " ed.)");
        holder.txtAuthors.setText(book.get_authors());
        holder.txtISBN.setText(book.get_isbn());
        holder.txtCondition.setText(book.get_condition().toString());
        holder.txtPrice.setText("$" + String.format("%.2f", book.get_price()));
        holder.txtCourse.setText(book.get_dept() + " " + book.get_courseno());
        holder.txtComments.setText(getMiniComment(book.get_comments()));
        //holder.imgIcon.setImageResource(Book.icon);

        return row;
    }

    private String getEdStr(Book book){
        String edStr = String.valueOf(book.get_edition());
        char edNum = edStr.charAt(edStr.length() - 1);
        String suffix = "th";
        switch (edNum){
            case '1':  if ((edStr.length() >= 2 && edStr.charAt(edStr.length()-2) == '1'))
            {
                suffix = "th";
            } else
            {
                suffix = "st";
            }
                break;
            case '2': if (edStr.length() >= 2 && edStr.charAt(edStr.length()-2) == '1')
            {
                suffix = "th";
            } else
            {
                suffix = "nd";
            }
                break;
            case '3':  if (edStr.length() >= 2 && edStr.charAt(edStr.length()-2) == '1')
            {
                suffix = "th";
            } else
            {
                suffix = "rd";
            }
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
        String bookId;
        ImageView imgIcon;
        TextView txtTitle;
        TextView txtAuthors;
        TextView txtISBN;
        TextView txtCondition;
        TextView txtPrice;
        TextView txtCourse;
        TextView txtComments;
        Button interestBtn;
    }
}