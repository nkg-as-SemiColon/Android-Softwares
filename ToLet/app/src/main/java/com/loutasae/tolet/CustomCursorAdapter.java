package com.loutasae.tolet;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;


public class CustomCursorAdapter extends CursorAdapter{

    public CustomCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView city = (TextView) view.findViewById(R.id.city);
        TextView locality = (TextView) view.findViewById(R.id.locality);
        TextView rent = (TextView) view.findViewById(R.id.rent);
        TextView date = (TextView) view.findViewById(R.id.date);
        //EditText remarks = (EditText) view.findViewById(R.id.editText8);

        String cit = cursor.getString(cursor.getColumnIndexOrThrow(MyDBManager.COLUMN_CITY));
        String localit = cursor.getString(cursor.getColumnIndexOrThrow(MyDBManager.COLUMN_LOCALITY));
        int ren = cursor.getInt(cursor.getColumnIndexOrThrow(MyDBManager.COLUMN_RENT));
        String dat = cursor.getString(cursor.getColumnIndexOrThrow(MyDBManager.COLUMN_DATE));
        String remark = cursor.getString(cursor.getColumnIndexOrThrow(MyDBManager.COLUMN_REMARKS));

        city.setText(cit);
        locality.setText(localit);
        rent.setText(""+ren);
        date.setText(dat);
        //remarks.setText(remark);

    }


}
