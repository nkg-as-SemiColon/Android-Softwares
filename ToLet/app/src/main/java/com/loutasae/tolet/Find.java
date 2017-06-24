package com.loutasae.tolet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Toast;

public class Find extends AppCompatActivity {

    public static final String TAG = "Formal";
    public MyDBManager MyDB = new MyDBManager(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoToletedClicked(v);
                    }
                }
        );
    }

    public void GoToletedClicked(View  v){
        Log.i(TAG,"Gotoleted clicked");
        popoulateListView();
    }


    void popoulateListView(){
        //MyDB.remove("City");
        Cursor cursor = MyDB.getallrows();
        EditText city = (EditText) findViewById(R.id.editText);
        EditText locality = (EditText) findViewById(R.id.editText2);
        EditText rent = (EditText) findViewById(R.id.editText6);
        EditText date = (EditText) findViewById(R.id.editText7);
        EditText remarks = (EditText) findViewById(R.id.editText8);

        viewlist(cursor);

    }

    void viewlist(Cursor cursor){
        setContentView(R.layout.output_list);
        ListView mist = (ListView) findViewById(R.id.id_listView);
        CustomCursorAdapter myCursorAdapter = new CustomCursorAdapter(getBaseContext(),cursor);
        if(mist != null){
            mist.setAdapter(myCursorAdapter);
        }
        else{
            Log.i(TAG,"Null listView");
        }

        waitforClick();
    }

    public void waitforClick() {
        ListView list = (ListView) findViewById(R.id.id_listView);
        list.

    }


    public void waitforClick() {
        ListView list = (ListView) findViewById(R.id.id_listView);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Car clickedcar = mycars.get(position);
                        String toOut = "You just clicked car at Position " + position + " having brand " + clickedcar.getBrand()
                                + " Number " + clickedcar.getNumb() + " and of " + clickedcar.getColor() + " colour.";

                        Toast.makeText(MainActivity.this,toOut,Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

}
