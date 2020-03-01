package com.example.accountandpass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddAccount extends AppCompatActivity {

    private TextView textViewUserName;
    private EditText editTextAccountName, editTextUserName, editTextPassword;
    private Button buttonAddAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        textViewUserName = findViewById(R.id.textViewUserName);
        editTextAccountName = findViewById(R.id.editAccountName);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonAddAccount = findViewById(R.id.buttonAddAccount);

        String greeting = "Hello " + getIntent().getStringExtra("UserName") + " !";
        textViewUserName.setText(greeting);
    }
}
