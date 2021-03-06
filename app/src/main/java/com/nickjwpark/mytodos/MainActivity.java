package com.nickjwpark.mytodos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//MyTodo list with id/password protection

public class MainActivity extends AppCompatActivity {

    EditText editTextId;
    EditText editTextPassword;
    Button btnLogin;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        sharedPref = this.getSharedPreferences("com.nickjwpark.mytodos", Context.MODE_PRIVATE);
        String defaultValue = "";
        String savedId = sharedPref.getString("id", defaultValue);
        String savedPassword = sharedPref.getString("password", defaultValue);
        editTextId.setText(savedId);
        editTextPassword.setText(savedPassword);


        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //correct id and password
                String id = "nick";
                String password = "park";

                //input id and password
                String inputId = editTextId.getText().toString();
                String inputPassword = editTextPassword.getText().toString();

                if(!id.equals(inputId)){
                    toast("아이디가 일치하지 않습니다");
                } else if(!password.equals(inputPassword)){
                    toast("비밀번호가 일치하지 않습니다");
                } else {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("id", inputId);
                    editor.putString("password", inputPassword);
                    editor.commit();

                    startTodoActivity(id);
                }
            }
        });
    }

    //start TodoActivity
    public void startTodoActivity(String id){
        Activity fromActivity = MainActivity.this;
        Class toActivity = TodoActivity.class;
        Intent intent = new Intent(fromActivity, toActivity);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    //helper toast function
    public void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
