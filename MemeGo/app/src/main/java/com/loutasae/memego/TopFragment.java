package com.loutasae.memego;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

public class TopFragment extends Fragment {

    public static EditText toptxt;
    public static EditText belowtxt;



    public interface TopSectionListener{
        public void createMeme(String top, String buttom);
    }

    TopSectionListner activityCommander;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            activityCommander = (TopSectionListner) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.topfragment,container,false);

        toptxt = (EditText) view.findViewById(R.id.toptxt);
        belowtxt = (EditText) view.findViewById(R.id.belowtxt);
        final Button btn = (Button)view.findViewById(R.id.btn);

        btn.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        buttonClicked(v);
                    }
                }
        );

        return view;
    }

    public void buttonClicked(View view){
        activityCommander.createMeme(toptxt.getText().toString(),belowtxt.getText().toString());
    }
}
