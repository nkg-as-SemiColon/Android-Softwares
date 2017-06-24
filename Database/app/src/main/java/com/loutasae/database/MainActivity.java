package com.loutasae.database;

import android.content.Context;
import android.support.v4.view.ScrollingView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    public DB_Manager DBMan;
    public static final String TAG = "Formal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBMan = new DB_Manager(MainActivity.this, null, null, 1);
        Button add = (Button) findViewById(R.id.newadd);
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addButtonClicked(v);
                    }
                }
        );

        Button remove = (Button) findViewById(R.id.remove);
        remove.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG,"remove button clicked");
                        removeButtonClicked(v);
                    }
                }
        );

    }


    public void addButtonClicked(View v){
        EditText name = (EditText) findViewById(R.id.name);
        String input = name.getText().toString();

        Product p = new Product(input);

        DBMan.addnewproduct(p);

        TextView txt2 = (TextView) findViewById(R.id.textView2);
        txt2.setText(DBMan.converttostring());
        name.setText("");
    }


    public void removeButtonClicked(View v){
        EditText name = (EditText) findViewById(R.id.name);
        String input = name.getText().toString();

        //Log.i(TAG,"The selected text is "+input);

        DBMan.remove(input);
        TextView txt2 = (TextView) findViewById(R.id.textView2);
        String s = DBMan.converttostring();
        name.setText("");
        txt2.setText(s);

    }

}
