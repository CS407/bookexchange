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
import com.cs407.bookexchange.connectors.buyers.MakeTransactionConnector;
import com.cs407.bookexchange.db.TableDefs;
import com.cs407.bookexchange.db.User;
import com.cs407.bookexchange.userprefs.Constants;
import com.cs407.bookexchange.userprefs.UserPrefs;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssunny7 on 4/28/2016.
 */
public class BuyersAdapter extends ArrayAdapter<User> {
    private ArrayList<User> buyers;
    private String bookid;
    private BuyersDialog parent;

    public BuyersAdapter(Context _context, String _bookid, ArrayList<User> _buyers, BuyersDialog _parent) {
        super(_context, 0, _buyers);

        buyers = _buyers;
        parent = _parent;
        bookid = _bookid;
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
    public View getView(final int position, View convertView, final ViewGroup parentGroup) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listelement_buyer, parentGroup, false);
        }

        TextView name = (TextView)convertView.findViewById(R.id.tvNameListElementBuyer);
        name.setText(getItem(position).get_name());

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(getContext()).
                        setTitle("Select Buyer").
                        setMessage("Do you want to connect to this buyer to sell the book? This will remove all other buyers from the list.").
                        setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                HashMap<String, String> params = new HashMap<String, String>();
                                params.put(TableDefs.Users.COLUMN_EMAIL, UserPrefs.readPreference(Constants.PREF_CUR_USER_EMAIL));
                                params.put(TableDefs.Users.COLUMN_PHONE, UserPrefs.readPreference(Constants.PREF_CUR_USER_PHONE));
                                params.put(TableDefs.Buyers.COLUMN_USERID, getItem(position).get_userid());
                                params.put(TableDefs.Buyers.COLUMN_BOOKID, bookid);

                                MakeTransactionConnector transactionConnector = new MakeTransactionConnector(parent);
                                transactionConnector.execute(params);
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

        return convertView;
    }
}