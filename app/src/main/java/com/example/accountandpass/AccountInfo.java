package com.example.accountandpass;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class AccountInfo extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewAccountNameInfo, textViewUserNameInfo, textViewPasswordInfo, textViewOtherInfo;
    private Button buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        textViewAccountNameInfo = (TextView)findViewById(R.id.textViewAccountNameInfo);
        textViewUserNameInfo = (TextView)findViewById(R.id.textViewUserNameInfo);
        textViewPasswordInfo = (TextView)findViewById(R.id.textViewPasswordInfo);
        textViewOtherInfo = (TextView)findViewById(R.id.textViewOtherInfo);
        buttonUpdate = (Button)findViewById(R.id.buttonUpdate);
        buttonDelete = (Button)findViewById(R.id.buttonDelete);

        String userNameInfo = "Username: " + getIntent().getStringExtra("USER_NAME");
        String passwordInfo = "Password: " + getIntent().getStringExtra("PASSWORD");
        String otherInfo = getIntent().getStringExtra("OTHER_INFO");
        String accountNameInfo = getIntent().getStringExtra("ACCOUNT_NAME");

        textViewAccountNameInfo.setText(accountNameInfo);
        textViewUserNameInfo.setText(userNameInfo);
        textViewPasswordInfo.setText(passwordInfo);
        textViewOtherInfo.setText(otherInfo);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    private void updateInfo(String accountName){
        /***************************************
         ***************************************
         Initialize and Showing update_info
         after hit update button
         ***************************************
         ***************************************/
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.update_info, null);
        TextView textViewAccountNameInfoUpdate = (TextView) dialogView.findViewById(R.id.textViewAccountNameUpdate);
        Button updateButton = dialogView.findViewById(R.id.updateButton);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);

        textViewAccountNameInfoUpdate.setText("Updating Account: "+accountName);

        dialogBuilder.setView(dialogView);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        /***************************************
         ***************************************
         Button Listeners of update_info
         ***************************************
         ***************************************/
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ETUserName = dialogView.findViewById(R.id.ETUserName);
                EditText ETPassword = dialogView.findViewById(R.id.ETPassword);
                EditText ETOther = dialogView.findViewById(R.id.ETOther);
                String userName = ETUserName.getText().toString().trim();
                String password = ETPassword.getText().toString().trim();
                String other = ETOther.getText().toString().trim();

                alertDialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }

    /*
    private boolean updateAccount(){
        // Getting the particular artist that need to be updated
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("artists").child(id);
        // create a new artist with new id, name and genre
        Artist artist = new Artist(id, name, genre);

        // Over-writting the new artist into a specified id
        databaseReference.setValue(artist);

        Toast.makeText(this, "Artist is Updated Successfully", Toast.LENGTH_LONG).show();

        return true;
    }*/

    @Override
    public void onClick(View v) {
        String accountNameInfo = getIntent().getStringExtra("ACCOUNT_NAME");
        if(v == buttonUpdate){
            updateInfo(accountNameInfo);
        }
        if(v == buttonDelete){
            //deleteInfo();
        }
    }
}
