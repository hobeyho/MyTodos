package com.nickjwpark.mytodos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TodoActivity extends AppCompatActivity {

    TextView textViewGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        textViewGreeting = (TextView) findViewById(R.id.textViewGreeting);

        Bundle extras = getIntent().getExtras();
        String id = "";
        if (extras != null) {
            id = extras.getString("id"); //get extra with key of "id" and set it to variable id
        }

        textViewGreeting.setText("Hello " + id + "!");

    }
}
