package com.cs407.bookexchange.UserUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.db.User;

import java.util.ArrayList;

/**
 * Created by ssunny7 on 4/28/2016.
 */
public class BuyersAdapter extends ArrayAdapter<User> {
    private ArrayList<User> buyers;
    private BuyersDialog parent;

    public BuyersAdapter(Context _context, ArrayList<User> _buyers, BuyersDialog _parent) {
        super(_context, 0, _buyers);

        buyers = _buyers;
        parent = _parent;
        if(buyers.size() == 0)
            parent.listEmpty();
    }

    public interface BuyersAdapterNotifier {
        void listEmpty();
        void listUnempty();
    }

    public void resetList(ArrayList<User> _buyers) {
        buyers.clear();
        buyers.addAll(_buyers);
        notifyDataSetChanged();

        if(_buyers.size() == 0)
            parent.listEmpty();
        else
            parent.listUnempty();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listelement_buyer, parent, false);
        }

        TextView name = (TextView)convertView.findViewById(R.id.tvNameListElementBuyer);
        name.setText(getItem(position).get_name());

        return convertView;
    }
}