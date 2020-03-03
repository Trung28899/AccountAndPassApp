package com.example.accountandpass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        textViewPasswordInfo = (TextView)findViewById(R.id.textViewAccountNameInfo);
        textViewOtherInfo = (TextView)findViewById(R.id.textViewOtherInfo);
        buttonUpdate = (Button)findViewById(R.id.buttonUpdate);
        buttonDelete = (Button)findViewById(R.id.buttonDelete);

        String accountNameInfo = getIntent().getStringExtra("ACCOUNT_NAME");
        String userNameInfo = getIntent().getStringExtra("USER_NAME");
        String passwordInfo = getIntent().getStringExtra("PASSWORD");
        String otherInfo = getIntent().getStringExtra("OTHER_INFO");

        textViewAccountNameInfo.setText(accountNameInfo);
        textViewUserNameInfo.setText(userNameInfo);
        textViewPasswordInfo.setText(passwordInfo);
        textViewOtherInfo.setText(otherInfo);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){

        }
        if(v == buttonDelete){
            
        }
    }
}
