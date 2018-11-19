package com.mukul.assignment.home.tabs;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mukul.assignment.R;
import com.mukul.assignment.models.PostList;
import com.mukul.assignment.models.Skill;
import com.mukul.assignment.models.WorkFunction;
import com.mukul.assignment.viewmodels.PostListViewModel;

import java.util.List;


public class Tab2 extends Fragment {

    TextView tvText2;
    private PostListViewModel postListViewModel;
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab2, container, false);
        tvText2 = view.findViewById(R.id.tv_tab2);

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
            List<WorkFunction> workFunctionList = postLists.get(0).getData().getWorkFunctions();
            for(WorkFunction workFunction : workFunctionList)
            {
                stringBuilder.append(workFunction.getName()+" | ");
            }

            tvText2.setText(stringBuilder.toString().substring(0,stringBuilder.toString().length()-2));
        }
    }
}
