package com.loutasae.memego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


/**
public class MainActivity extends AppCompatActivity implements TopFragment.TopSectionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void createMeme(String top, String buttom) {
        BelowFragment blwFragmnt = (BelowFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        blwFragmnt.setMemetxt(top, buttom);
    }


}


package aybars.arslan.fragments;

        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;

*/

public class MainActivity extends ActionBarActivity implements TopFragment.TopSectionListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //This code called by the Top Fragment called when the user clicked the button
    @Override
    public void createMeme(String top, String bottom) {
        BelowFragment blwFragmnt = (BelowFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        blwFragmnt.setMemetxt(top, bottom);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}