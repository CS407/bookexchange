package com.cs407.bookexchange.UserUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cs407.bookexchange.R;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listelement_seller_book, parent, false);
        }

        TextView title = (TextView)convertView.findViewById(R.id.sellerBookTitle);
        title.setText(book.get_title());

        return convertView;
    }
}
