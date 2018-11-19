package com.mukul.assignment.viewmodels;

import com.mukul.assignment.home.HomeView;
import com.mukul.assignment.models.PostList;
import com.mukul.assignment.networking.NetworkError;
import com.mukul.assignment.networking.Service;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mukul on 6/25/16.
 */
public class ViewModelPresenter {
    private final Service service;
    private final HomeView view;
    private CompositeSubscription subscriptions;

    public ViewModelPresenter(Service service, HomeView view) {
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
