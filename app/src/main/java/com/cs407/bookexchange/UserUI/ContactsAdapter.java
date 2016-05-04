package com.cs407.bookexchange.UserUI;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cs407.bookexchange.R;
import com.cs407.bookexchange.db.Contact;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssunny7 on 5/2/2016.
 */
public class ContactsAdapter extends ArrayAdapter<Contact> {
    private ArrayList<Contact> contacts;

    public ContactsAdapter(Context context, ArrayList<Contact> _contacts) {
        super(context, 0, _contacts);

        contacts = _contacts;
    }

    public void resetList(ArrayList<Contact> _contacts) {
        contacts.clear();
        contacts.addAll(_contacts);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parentGroup) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listelement_contact, parentGroup, false);
        }

        Contact contact = getItem(position);
        TextView name = (TextView)convertView.findViewById(R.id.tvSellerContactListElement);
        name.setText(contact.get_name());
        TextView title = (TextView)convertView.findViewById(R.id.tvBookTiteContactListElement);
        title.setText(contact.get_bookTitle());
        TextView price = (TextView)convertView.findViewById(R.id.tvPriceContactListElement);
        price.setText("$ " + ((Double)contact.get_price()).toString());
        TextView email = (TextView)convertView.findViewById(R.id.tvEmailContactListElement);
        email.setText(contact.get_email());
        TextView phone = (TextView)convertView.findViewById(R.id.tvPhoneContactListElement);
        phone.setText(contact.get_phone());

        return convertView;
    }
}
