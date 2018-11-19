package com.mukul.assignment.home;

import com.mukul.assignment.models.PostList;

/**
 * Created by mukul on 6/25/16.
 */
public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityListSuccess(PostList postListResponse);

}
