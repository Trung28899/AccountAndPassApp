package com.example.accountandpass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

class storedAccount{
    String accountName;
    String userName;
    String password;
    String otherInfo;

    public storedAccount(){

    }

    public storedAccount(String accountName, String userName, String password, String otherInfo) {
        this.accountName = accountName;
        this.userName = userName;
        this.password = password;
        this.otherInfo = otherInfo;
    }

    public String getAccountName() {
        return accountName;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getOtherInfo(){return  otherInfo; }
}

public class AddAccount extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewUserName;
    private EditText editTextAccountName, editTextUserName, editTextPassword;
    private Button buttonAddAccount;
    private ProgressDialog progressDialog;
    private ListView listViewAccounts;
    List<storedAccount> listOfAccounts;

    DatabaseReference databaseStoredAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        textViewUserName = findViewById(R.id.textViewUserName);
        editTextAccountName = findViewById(R.id.editAccountName);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonAddAccount = findViewById(R.id.buttonAddAccount);
        listViewAccounts = findViewById(R.id.listViewAccount);
        listOfAccounts = new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        String userNamePassed = getIntent().getStringExtra("UserName");
        String greeting = "Hello " + userNamePassed + " !";
        String userNameStoredAccounts = "accounts/" + userNamePassed + "/SavedAccount";
        textViewUserName.setText(greeting);

        databaseStoredAccounts = FirebaseDatabase.getInstance().getReference(userNameStoredAccounts);

        buttonAddAccount.setOnClickListener(this);

        // Item Click Listener for Items in ListView
        listViewAccounts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                storedAccount storedAccounts = listOfAccounts.get(position);
                Intent intent = new Intent(getApplicationContext(), AccountInfo.class);

                intent.putExtra("ACCOUNT_NAME", storedAccounts.getAccountName());
                intent.putExtra("USER_NAME", storedAccounts.getUserName());
                intent.putExtra("PASSWORD", storedAccounts.getPassword());
                intent.putExtra("OTHER_INFO", storedAccounts.getOtherInfo());
                //intent.putExtra("DATABASE", userNameStoredAccounts);


                startActivity(intent);
            }
        });
    }

    private void addAccount(){
        String accountName = editTextAccountName.getText().toString().trim();
        String userNameAdd = editTextUserName.getText().toString().trim();
        String passwordAdd = editTextPassword.getText().toString().trim();
        StringBuilder id = new StringBuilder(accountName);

        if(TextUtils.isEmpty(accountName)){
            // email is empty
            Toast.makeText(this, "Please enter your Account Name", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        if(TextUtils.isEmpty(userNameAdd)){
            //password is empty
            Toast.makeText(this, "Please enter your UserName", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        if(TextUtils.isEmpty(passwordAdd)){
            //password is empty
            Toast.makeText(this, "Please confirm your Password", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        progressDialog.setMessage("Adding Account...");
        progressDialog.show();

        storedAccount storedAccount = new storedAccount(accountName, userNameAdd, passwordAdd, "");
        databaseStoredAccounts.child(id.toString()).setValue(storedAccount);
    }

    // function to continuously update database
    @Override
    protected void onStart() {
        super.onStart();

        databaseStoredAccounts.addValueEventListener(new ValueEventListener() {
            @Override
            // This function will be executed every time we change anything inside in database
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listOfAccounts.clear();

                // Loop through all the values of the database
                for(DataSnapshot trackSnapshot : dataSnapshot.getChildren()){
                    storedAccount track = trackSnapshot.getValue(storedAccount.class);
                    listOfAccounts.add(track);
                }

                // showing the list on AddTrackActivity
                AccountList accountListAdapter = new AccountList(AddAccount.this, listOfAccounts);
                listViewAccounts.setAdapter(accountListAdapter);
            }
            // Function will be run any time an error happens
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAddAccount){
            addAccount();
        }
    }
}
