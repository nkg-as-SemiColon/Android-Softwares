package com.loutasae.memego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import android.os.Environment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.EditText;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.content.res.Resources;
import android.util.TypedValue;



public class Text extends AppCompatActivity {

    Bitmap originalBitmap,image,UndoImg=null;
    ImageView imageView;
    EditText editText;
    Paint paint;
    String iname,root;
    int text_size = 100;
    Canvas undoCanvas;

    public static final String TAG = "NKG";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        Bundle b = getIntent().getExtras();
        if(b==null){
            return;
        }

        String picturePath = b.getString("picturePath");

        imageView = (ImageView) findViewById(R.id.img_txt);
        //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        int scaledSize = getResources().getDimensionPixelSize(R.dimen.myFontSize2);
        //Resources r = getResources();
        //float px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,text_size,r.getDisplayMetrics());

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(scaledSize);
        //paint.setTextSize();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int height1 = displaymetrics.heightPixels;
        int width1 = displaymetrics.widthPixels;

        originalBitmap = (BitmapFactory.decodeFile(picturePath));
        originalBitmap = Bitmap.createScaledBitmap(originalBitmap, width1, height1, false);
        //originalBitmap = Bitmap.createScaledBitmap(originalBitmap, originalBitmap.getWidth(), originalBitmap.getHeight(), false);
        //Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
        imageView.setImageBitmap(originalBitmap);

        image = originalBitmap.copy(Bitmap.Config.RGB_565, true);

        editText = (EditText) findViewById(R.id.text);

        Button btn_clr_all = (Button) findViewById(R.id.refresh);

        btn_clr_all.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                image = originalBitmap.copy(Bitmap.Config.RGB_565, true);
                imageView.setImageBitmap(image);
            }
        });

        Button gomemed = (Button) findViewById(R.id.gomemed);
        gomemed.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Text.this,FinalView.class);
                saveImage(image);
                i.putExtra("root",root);
                i.putExtra("iname",iname);
                //i.putExtra("image",image);
                startActivity(i);
            }
        });

        imageView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                String user_text = editText.getText().toString();

                float scr_x = arg1.getX();
                float scr_y = arg1.getY();

                Log.i("TAG","Point " + scr_x + " " + scr_y);

                createImage(scr_x, scr_y, user_text);
                return true;
            }
        });
    }

    public Bitmap createImage(float scr_x,float scr_y,String user_text){

        Canvas canvas = new Canvas(image);
        //int viewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();

        //scr_y=scr_y- viewTop;
        if(UndoImg != null) {
            Log.i("TAG","Canvas Not NULL");
            //undoCanvas = new Canvas(UndoImg);
        }

        UndoImg = image.copy(image.getConfig(), true);

        canvas.drawText(""+user_text, scr_x, scr_y, paint);
        imageView.setImageBitmap(image);
        return image;
    }

    private void saveImage(Bitmap finalBitmap) {
        Log.i(TAG,"saving began!");
        root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

        File myDir = new File(root + "/MemeGo");

        if (!myDir.exists()) {
            myDir.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        //n++;
        iname = "Image-" + timeStamp + ".jpg";
        File file = new File(myDir, iname);

        Log.i("TAG","Text iname"+iname);
        Log.i("TAG","Text myDir"+myDir);

        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            //Toast.makeText(this,"Image saved at InternalStorage/Pictures/MemeGo/...",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"Oops! Some Error occured...",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_colour,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Undo:
                imageView.setImageBitmap(UndoImg);
                image = UndoImg.copy(UndoImg.getConfig(), true);
                Toast.makeText(this,"Previos state Restored... Only one undo is allowed each time...", Toast.LENGTH_LONG).show();
                return true;

            case R.id.Black:
                Toast.makeText(this,"Text changed to black...",Toast.LENGTH_LONG).show();
                paint.setColor(Color.BLACK);
                return true;

            case R.id.white:
                Toast.makeText(this,"Text changed to White...",Toast.LENGTH_LONG).show();
                paint.setColor(Color.WHITE);
                return true;

            case R.id.Blue:
                Toast.makeText(this,"Text changed to Blue...",Toast.LENGTH_LONG).show();
                paint.setColor(Color.BLUE);
                return true;

            case R.id.s_10:
                Toast.makeText(this,"Size changed to 10...",Toast.LENGTH_LONG).show();
                paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.myFontSize1));
                return true;
            case R.id.s_20:
                Toast.makeText(this,"Size changed to 20...",Toast.LENGTH_LONG).show();
                paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.myFontSize2));
                return true;
            case R.id.s_30:
                Toast.makeText(this,"Size changed to 30...",Toast.LENGTH_LONG).show();
                paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.myFontSize3));
                return true;
            case R.id.s_40:
                Toast.makeText(this,"Size changed to 40...",Toast.LENGTH_LONG).show();
                paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.myFontSize4));
                return true;
            case R.id.s_50:
                Toast.makeText(this,"Size changed to 50...",Toast.LENGTH_LONG).show();
                paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.myFontSize5));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
