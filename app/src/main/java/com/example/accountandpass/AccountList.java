package com.example.accountandpass;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AccountList extends ArrayAdapter<storedAccount> {
    private Activity context;
    private List<storedAccount> listOfAccounts;

    public AccountList(Activity context, List<storedAccount> listOfAccounts){
        super(context, R.layout.list_layout, listOfAccounts);
        this.context = context;
        this.listOfAccounts = listOfAccounts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewListingUserName = (TextView) listViewItem.findViewById(R.id.textViewListingUserName);
        TextView textViewListingPassword = (TextView) listViewItem.findViewById(R.id.textViewListingPassword);

        storedAccount storedAccounts = listOfAccounts.get(position);

        String UserName = "Username: " + storedAccounts.getUserName();
        String Password = "Password: " + storedAccounts.getPassword();

        textViewName.setText(storedAccounts.getAccountName());
        textViewListingUserName.setText(UserName);
        textViewListingPassword.setText(Password);

        return listViewItem;
    }
}
