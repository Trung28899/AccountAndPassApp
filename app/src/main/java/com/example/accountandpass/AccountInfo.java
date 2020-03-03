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
    public String databasePath, accountNameInfo;

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
        String otherInfo = "Other Info: "+getIntent().getStringExtra("OTHER_INFO");
        accountNameInfo = getIntent().getStringExtra("ACCOUNT_NAME");
        databasePath = getIntent().getStringExtra("DATABASE");

        textViewAccountNameInfo.setText(accountNameInfo);
        textViewUserNameInfo.setText(userNameInfo);
        textViewPasswordInfo.setText(passwordInfo);

        if(otherInfo.equals("Other Info: ")){
            textViewOtherInfo.setText("");
        } else {
            textViewOtherInfo.setText(otherInfo);
        }

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
                String id = accountNameInfo;

                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(getApplicationContext(), "Please update your Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Please update your Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(databasePath).child(id);
                storedAccount storedAccount = new storedAccount(id,userName, password, other);
                databaseReference.setValue(storedAccount);

                Toast.makeText(getApplicationContext(), "Account is Updated Successfully", Toast.LENGTH_LONG).show();

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

    private void deleteInfo(){
        /***************************************
         ***************************************
         Initialize and Showing update_info
         after hit update button
         ***************************************
         ***************************************/
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.delete_confirmation, null);

        TextView textViewDelete = (TextView) dialogView.findViewById(R.id.textViewDelete);
        Button confirmButton = dialogView.findViewById(R.id.buttonConfirm);
        Button noButton = dialogView.findViewById(R.id.buttonNo);

        textViewDelete.setText("Do you want to Delete "+accountNameInfo+" ?");

        dialogBuilder.setView(dialogView);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        /***************************************
         ***************************************
         Button Listeners of update_info
         ***************************************
         ***************************************/

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = accountNameInfo;
                DatabaseReference dbAccount = FirebaseDatabase.getInstance().getReference(databasePath).child(id);

                alertDialog.cancel();
                dbAccount.removeValue();

                Toast.makeText(getApplicationContext(), "Account is Deleted !", Toast.LENGTH_LONG).show();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

    }

    @Override
    public void onClick(View v) {
        String accountNameInfo = getIntent().getStringExtra("ACCOUNT_NAME");
        if(v == buttonUpdate){
            updateInfo(accountNameInfo);
        }
        if(v == buttonDelete){
            deleteInfo();
        }
    }
}