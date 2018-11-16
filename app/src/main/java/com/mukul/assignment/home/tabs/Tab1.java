package com.mukul.assignment.home.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mukul.assignment.R;


public class Tab1 extends Fragment {

    TextView tvText1;
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1, container, false);
        tvText1 = view.findViewById(R.id.tv_tab1);
        //return inflater.inflate(R.layout.tab1, container, false);
        return view;
    }
}
