package com.team16.ecoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView forgot, login, create,name,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgot = findViewById(R.id.forgot);
        login = findViewById(R.id.ty);
        name = findViewById(R.id.editText);
        pass =findViewById(R.id.editText2);
        create = findViewById(R.id.create_acc);
        forgot.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, forgotpass1.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
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
        


    }

    public void newProduct (View view) {
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
}
