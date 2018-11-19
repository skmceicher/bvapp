package com.mukul.assignment.home.tabs;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mukul.assignment.R;
import com.mukul.assignment.models.Industry;
import com.mukul.assignment.models.PostList;
import com.mukul.assignment.models.WorkFunction;
import com.mukul.assignment.viewmodels.PostListViewModel;

import java.util.List;


public class Tab3 extends Fragment {

    TextView tvText3;
    private PostListViewModel postListViewModel;
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab3, container, false);
        tvText3 = view.findViewById(R.id.tv_tab3);

        postListViewModel = ViewModelProviders.of(getActivity()).get(PostListViewModel.class);
        postListViewModel.loadPosListLiveData(getActivity());
        postListViewModel.getPosListLiveData().observe(getActivity(), postLists -> updateUI(postLists));

        return view;
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable List<PostList> postLists){
        if (postLists != null){

            StringBuilder stringBuilder = new StringBuilder();
            List<Industry> industryList = postLists.get(0).getData().getIndustries();
            for(Industry industry : industryList)
            {
                stringBuilder.append(industry.getName()+" | ");
            }

            tvText3.setText(stringBuilder.toString().substring(0,stringBuilder.toString().length()-2));
        }
    }
}
