package com.loutasae.memego;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BelowFragment extends Fragment {

    private static TextView memetxt1;
    private static TextView memetxt2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.buttomfragment,container,false);

        memetxt1 = (TextView) view.findViewById(R.id.memetxt1);
        memetxt2 = (TextView) view.findViewById(R.id.memetxt2);

        return view;
    }

    public void setMemetxt(String top, String btm){
        memetxt1.setText(top);
        memetxt2.setText(btm);
    }


}
