package com.team16.ecoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    AutoCompleteTextView name;
    TextView forgot, create,login,pass;
    CheckBox remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgot = findViewById(R.id.forgot);
        login = findViewById(R.id.ty);
        name = findViewById(R.id.editText);
        pass =findViewById(R.id.editText2);
        create = findViewById(R.id.create_acc);
        remember = findViewById(R.id.ch_rememberme);
        forgot.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, forgotpass1.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (remember.isChecked())
                    newProduct();
                else
                    forgetUser();
                Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                startActivity(intent);
            }
        });
        create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        final MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        ArrayList<String> users = dbHandler.returnUsers();
        ArrayAdapter autocompletetextAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line, users);

        name.setAdapter(autocompletetextAdapter);

        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
            Data a = dbHandler.findUser(name.getText().toString());
            if (a==null) return;
            pass.setText(a.getPass());
            remember.setChecked(true);
            }

        });
    }

    public void newProduct () {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String productName = name.getText().toString();
        String quantity = pass.toString();
        if (!productName.equals("") &&  !quantity.equals("")){
            Data found = dbHandler.findUser(name.getText().toString());
            if (found == null){
                Data product = new Data(name.getText().toString(),pass.getText().toString());
                dbHandler.addUser(product);
                name.setText("");
                pass.setText("");
            }
        }
    }

    public void forgetUser(){
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        String uname = name.getText().toString();
        if (!uname.equals("")){
            Data found = dbHandler.findUser(uname);
            if (found != null){
                dbHandler.deleteUser(found.getDataName());
                name.setText("");
                pass.setText("");
            }
        }
    }
}
