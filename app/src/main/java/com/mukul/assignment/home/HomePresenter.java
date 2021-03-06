package com.mukul.assignment.home;

import android.util.Log;

import com.mukul.assignment.models.PostList;
import com.mukul.assignment.networking.NetworkError;
import com.mukul.assignment.networking.Service;
import com.mukul.assignment.viewmodels.PostListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mukul on 6/25/16.
 */
public class HomePresenter {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(Service service, HomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCityList() {
        view.showWait();

        Subscription subscription = service.getCityList(new Service.GetCityListCallback() {
            @Override
            public void onSuccess(PostList cityListResponse) {

                view.removeWait();
                view.getCityListSuccess(cityListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }

}
