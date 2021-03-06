package com.nickjwpark.mytodos;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoActivity extends ListActivity {

    TextView textViewGreeting;
    Button btnAdd;
    ArrayList<String> todo_list;

    SharedPreferences sharedPref;

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


        sharedPref = this.getSharedPreferences("com.nickjwpark.mytodos", Context.MODE_PRIVATE);
        todo_list = new ArrayList<String>();
        loadArrayList();
        populateListview();

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addNewTodo();
            }
        });
    }

    protected void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);
        LogLibrary.print(position);
        String work = todo_list.get(position);
        LogLibrary.print(work);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(work);
        builder.setMessage("이 일이 다 끝났나요?");

        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //할일을 여기서 지워주자
                todo_list.remove(position);
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

    //open alert dialog to create a new todo
    public void addNewTodo(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("추가");
        builder.setMessage("할 일을 추가해 보세요");

        final EditText todo_input = new EditText(this);
        todo_input.setInputType(InputType.TYPE_CLASS_TEXT);
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
        saveArrayList();
    }

    public void saveArrayList(){
        String todo_str = "";
        for(String todo : todo_list){
            todo_str = todo_str + todo + "&&&";
            LogLibrary.print(todo_str);
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("todo", todo_str);
        editor.commit();
    }

    public void loadArrayList(){
        String defaultValue = "";
        String todo = sharedPref.getString("todo", defaultValue);
        String [] todo_arr = todo.split("&&&");
        LogLibrary.printEach(todo_arr);
        for(String work : todo_arr){
            todo_list.add(work);
        }
        LogLibrary.printEach(todo_list);
    }
}
