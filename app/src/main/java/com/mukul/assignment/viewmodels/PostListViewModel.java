package com.mukul.assignment.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.mukul.assignment.deps.DaggerDepsViewModel;
import com.mukul.assignment.deps.DepsViewModel;
import com.mukul.assignment.home.HomeView;
import com.mukul.assignment.models.PostList;
import com.mukul.assignment.networking.NetworkModule;
import com.mukul.assignment.networking.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PostListViewModel  extends ViewModel implements HomeView {
    //private BaseAppViewModel baseAppViewModel;
    @Inject
    public Service service;
    private MutableLiveData<List<PostList>> postListLiveData;
    /*public PostListViewModel() {
        if (postListLiveData == null) {
            Log.i("livedata :","constructor");
            postListLiveData = new MutableLiveData<>();
        }
    }
    public void savePostList(List<PostList> postList) {
        Log.i("livedata :","data saved");
        postListLiveData.postValue(postList);
    }
    public LiveData<List<PostList>> getPostList() {
        Log.i("livedata :","data get");
        return postListLiveData;
    }*/

    /*public PostListViewModel() {

    }*/

    // ----

    /*public void loadFruits() {

        if (postListLiveData == null) {
            postListLiveData = new MutableLiveData<>();
        }
            List<String> fruitsStringList = new ArrayList<>();
            fruitsStringList.add("Mango");
            fruitsStringList.add("Apple");

            postListLiveData.setValue(fruitsStringList);
            Log.i("livedata :","inside loadfruits"+postListLiveData.getValue());

    }*/

    // ----

    public void loadPosListLiveData(Context context) {

        if (postListLiveData == null) {
            postListLiveData = new MutableLiveData<>();
        }

        /*if(baseAppViewModel == null)
        {
            baseAppViewModel = new BaseAppViewModel();
        }*/
        //getDepsViewModel().inject(this);
        File cacheFile = new File(context.getCacheDir(), "responses");
        DepsViewModel depsViewModel = DaggerDepsViewModel.builder().networkModule(new NetworkModule(cacheFile)).build();
        depsViewModel.inject(this);

        ViewModelPresenter presenter = new ViewModelPresenter(service, this);
        presenter.getCityList();

        /*List<String> fruitsStringList = new ArrayList<>();
        fruitsStringList.add("Mango");
        fruitsStringList.add("Apple");

        postListLiveData.setValue(fruitsStringList);
        Log.i("livedata :","inside loadfruits"+postListLiveData.getValue());*/

    }

    public LiveData<List<PostList>> getPosListLiveData() {
        return this.postListLiveData;
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getCityListSuccess(PostList postListResponse) {
        List<PostList> postList = new ArrayList<>();
        postList.add(postListResponse);
        postListLiveData.setValue(postList);
        Log.i("livedata :","postListLiveData"+postListLiveData.getValue());
    }
}
