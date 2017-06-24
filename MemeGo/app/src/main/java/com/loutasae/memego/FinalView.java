package com.loutasae.memego;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.graphics.BitmapFactory;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FinalView extends AppCompatActivity {

    public static final String TAG = "NKG";
    String path;
    Bitmap imgtosave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_view);


        Bundle i = getIntent().getExtras();
        String prev_root = i.getString("root");
        String prev_iname = i.getString("iname");

        path = prev_root + "/MemeGo/" + prev_iname;

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(BitmapFactory.decodeFile(path));

        File myDr = new File(prev_root + "/MemeGo");
        File f = new File(myDr, prev_iname);

        Log.i("TAG","FileExists "+f.exists());
        imgtosave = (BitmapFactory.decodeFile(path));
        if (f.exists())
            f.delete();

    }

    private void saveImage(Bitmap finalBitmap) {
        Log.i(TAG,"saving began!");
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

        File myDir = new File(root + "/MemeGo");

        if (!myDir.exists()) {
            myDir.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        //n++;
        String iname = "Image-" + timeStamp + ".jpg";
        File file = new File(myDir, iname);

        Log.i("TAG","Fianl iname"+iname);
        Log.i("TAG","Final myDir"+myDir);


        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(this,"Image saved...",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"Oops! Image not saved... Some Error occured...",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                //Toast.makeText(this,"Clicked save",Toast.LENGTH_LONG).show();
                saveImage(imgtosave);
                return true;

            case R.id.Color:
                Toast.makeText(this,"Clicked Color",Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
