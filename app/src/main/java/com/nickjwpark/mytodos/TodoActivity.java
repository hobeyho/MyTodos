package com.nickjwpark.mytodos;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoActivity extends ListActivity {

    TextView textViewGreeting;
    Button btnAdd;
    ArrayList<String> todo_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        textViewGreeting = (TextView) findViewById(R.id.textViewGreeting);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        Bundle extras = getIntent().getExtras();
        String id = "";
        if (extras != null) {
            id = extras.getString("id"); //get extra with key of "id" and set it to variable id
        }
        textViewGreeting.setText("" + id + "의 할일들");

        todo_list = new ArrayList<String>();
        populateListview();

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addNewTodo();
            }
        });
    }

    //open alert dialog to create a new todo
    public void addNewTodo(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("추가");
        builder.setMessage("할 일을 추가해 보세요");

        final EditText todo_input = new EditText(this);
        todo_input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(todo_input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //할일을 여기서 저장하자
                String input = todo_input.getText().toString();
                todo_list.add(input);
                LogLibrary.printEach(todo_list); //LogLibrary 를 활용해 ArrayList 를 간단히 프린트 해 보자
                populateListview();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void populateListview(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, todo_list);
        setListAdapter(adapter);
    }
}
