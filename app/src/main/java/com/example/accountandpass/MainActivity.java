package com.example.accountandpass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName, password;
    private Button buttonLogin;
    private TextView textViewSignUp;
    DatabaseReference databaseAccounts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.editTextUserName);
        password = (EditText)findViewById(R.id.editTextPassword);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        textViewSignUp = (TextView)findViewById(R.id.textViewSignUp);
        databaseAccounts = FirebaseDatabase.getInstance().getReference().child("accounts");

        buttonLogin.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);



    }

    private void login(){

        databaseAccounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String loginUser = userName.getText().toString().trim();
                String loginPassword = password.getText().toString().trim();
                int count = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Account account = snapshot.getValue(Account.class);

                    if(loginUser.equals(account.getUserName())){

                        if(loginPassword.equals(account.getPassword())){
                            count++;
                            Toast.makeText(getApplicationContext(), "Logged In Successfully !", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), AddAccount.class);
                            intent.putExtra("UserName", loginUser);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect Password !", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                if(count == 0){
                    Toast.makeText(getApplicationContext(), "Incorrect UserName !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void signUp(){
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogin){
            login();
        }
        if(view == textViewSignUp){
            signUp();
        }
    }
}
