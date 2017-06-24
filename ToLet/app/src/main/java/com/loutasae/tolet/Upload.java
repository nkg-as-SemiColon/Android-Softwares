package com.loutasae.tolet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.text.InputType;
import android.view.View.OnClickListener;
import android.widget.DatePicker;

public class Upload extends AppCompatActivity implements OnClickListener {

    public static final String TAG = "Formal";
    public MyDBManager MyDB;
    SQLiteDatabase db;
    private EditText fromDateEtxt;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private String fromdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()); //dd-MM-yyyy

        findViewsByID();

        setDateTimeField();

    }

    private void findViewsByID() {
        fromDateEtxt = (EditText) findViewById(R.id.editText7);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromdate = dateFormatter.format(newDate.getTime());
                fromDateEtxt.setText(fromdate);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        }
    }


    public void UploadClicked(View view){
        Log.i(TAG,"1.Upload Button Clicked...");

        MyDB = new MyDBManager(Upload.this, null, null, 1);
        db = openOrCreateDatabase(MyDBManager.DATABASE_NAME, Context.MODE_PRIVATE, null);
        MyDB.onCreate(db);

        EditText city = (EditText) findViewById(R.id.editText);
        EditText locality = (EditText) findViewById(R.id.editText2);
        EditText rent = (EditText) findViewById(R.id.editText6);
        EditText date = (EditText) findViewById(R.id.editText7);
        EditText remarks = (EditText) findViewById(R.id.editText8);

        String cit = city.getText().toString();
        String localit = locality.getText().toString();
        String ren = rent.getText().toString();
        String dat = date.getText().toString();
        String remark = remarks.getText().toString();
        EachFlat flat = new EachFlat(cit,localit,ren,remark,dat);
        Log.i(TAG,"2.Upload Button Clicked...");
        MyDB.addnewflat(flat);
        clearall();
        Toast.makeText(getBaseContext(),"Uploaded Successfully!...",Toast.LENGTH_LONG).show();
    }

    public void clearall(){
        EditText city = (EditText) findViewById(R.id.editText);
        EditText locality = (EditText) findViewById(R.id.editText2);
        EditText rent = (EditText) findViewById(R.id.editText6);
        EditText date = (EditText) findViewById(R.id.editText7);
        EditText remarks = (EditText) findViewById(R.id.editText8);

        city.setText("");
        locality.setText("");
        rent.setText("");
        date.setText("");
        remarks.setText("");

    }
}
