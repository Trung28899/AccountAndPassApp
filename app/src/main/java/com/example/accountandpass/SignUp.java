package com.example.accountandpass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class Account{
    String userName;
    String password;

    public Account(){

    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
}

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText userNameSignUp, passwordSignUp, passwordConfirm;
    private Button buttonSignUp;
    private TextView textViewSignIn;
    private ProgressDialog progressDialog;

    DatabaseReference databaseAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userNameSignUp = (EditText)findViewById(R.id.editTextUserNameSignUp);
        passwordSignUp = (EditText)findViewById(R.id.editTextPasswordSignUp);
        passwordConfirm = (EditText)findViewById(R.id.editTextPasswordConfirm);
        buttonSignUp = (Button)findViewById(R.id.buttonSignUp);
        textViewSignIn = (TextView)findViewById(R.id.textViewLogin);
        progressDialog = new ProgressDialog(this);

        databaseAccounts = FirebaseDatabase.getInstance().getReference("accounts/Trung");

        buttonSignUp.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);

    }

    private void registerUser(){
        final String userNameSignU = userNameSignUp.getText().toString().trim();
        final String passwordSignU = passwordSignUp.getText().toString().trim();
        final String retypePassword = passwordConfirm.getText().toString();
        StringBuilder id = new StringBuilder(userNameSignU);

        if(TextUtils.isEmpty(userNameSignU)){
            // email is empty
            Toast.makeText(this, "Please enter your Username", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        if(TextUtils.isEmpty(passwordSignU)){
            //password is empty
            Toast.makeText(this, "Please enter your Password", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        if(TextUtils.isEmpty(retypePassword)){
            //password is empty
            Toast.makeText(this, "Please confirm your Password", Toast.LENGTH_SHORT).show();
            // Stop the function from executing further
            return;
        }

        if(!passwordSignU.equals(retypePassword)){
            Toast.makeText(this, "The confirm password didn't match the password entered", Toast.LENGTH_SHORT).show();
            return;
        }

        // if validations are ok
        // we will first show a progressDialog

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        Account accounts = new Account(userNameSignU, passwordSignU);
        databaseAccounts.child(id.toString()).setValue(accounts);

        Toast.makeText(this, "Sign Up successfully, please Login !", Toast.LENGTH_LONG).show();

        // passing id to the waitlist activity
        Intent intent =new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void signIn(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view == textViewSignIn){
            signIn();
        }
        if(view == buttonSignUp){
            registerUser();
        }
    }
}
