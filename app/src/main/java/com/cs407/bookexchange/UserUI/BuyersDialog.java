package com.cs407.bookexchange.UserUI;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.connectors.buyers.ReadBuyersConnector;
import com.cs407.bookexchange.db.User;

import java.util.ArrayList;

/**
 * Created by ssunny7 on 4/28/2016.
 */
public class BuyersDialog extends Dialog implements BuyersAdapter.BuyersAdapterNotifier {
    private Context context;
    private String bookid;
    private ListView buyersList;
    private TextView emptyTitle;
    private TextView unemptyTitle;
    private BuyersAdapter buyersAdapter;

    public BuyersDialog(Context _context, String _bookid) {
        super(_context);

        context = _context;
        bookid = _bookid;
    }

    public void listEmpty() {
        emptyTitle.setVisibility(View.VISIBLE);
        unemptyTitle.setVisibility(View.GONE);
    }

    public void listUnempty() {
        emptyTitle.setVisibility(View.GONE);
        unemptyTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_buyers);

        emptyTitle = (TextView)findViewById(R.id.tvNegativeDialogBuyers);
        unemptyTitle = (TextView)findViewById(R.id.tvPositiveDialogBuyers);
        buyersList = (ListView)findViewById(R.id.lvBuyersDialogBuyers);
        buyersAdapter = new BuyersAdapter(getContext(), bookid, new ArrayList<User>(), this);
        buyersList.setAdapter(buyersAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ReadBuyersConnector readBuyersConnector = new ReadBuyersConnector(buyersAdapter);
        readBuyersConnector.execute(bookid);
    }
}
