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
import com.mukul.assignment.viewmodels.PostListViewModel;

import java.util.List;


public class Tab1 extends Fragment {

    TextView tvText1;
    private PostListViewModel postListViewModel;
    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1, container, false);
        tvText1 = view.findViewById(R.id.tv_tab1);
        Log.i("livedata :","inside tab1 onCreateView");
        postListViewModel = ViewModelProviders.of(getActivity()).get(PostListViewModel.class);
        postListViewModel.loadPosListLiveData(getActivity());
        postListViewModel.getPosListLiveData().observe(getActivity(), postLists -> updateUI(postLists));
        return view;
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //this.configureDagger();
        this.configureViewModel();
    }

    private void configureViewModel(){
        Log.i("livedata :","inside tab1 configureViewModel");
        postListViewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        postListViewModel.init();
        postListViewModel.getPosListLiveData().observe(this, postLists -> updateUI(postLists));
    }*/

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable List<PostList> postLists){
        if (postLists != null){
            Log.i("livedata :","updateUI");
            StringBuilder stringBuilder = new StringBuilder();
            List<Skill> skillList = postLists.get(0).getData().getSkills();
            for(Skill skill : skillList)
            {
                stringBuilder.append(skill.getName()+" | ");
            }
            Log.i("Skill :","list " + stringBuilder.toString().substring(0,stringBuilder.toString().length()-2));
            tvText1.setText(stringBuilder.toString().substring(0,stringBuilder.toString().length()-2));
        }
    }

}
