package com.mukul.assignment.deps;


import com.mukul.assignment.home.HomeActivity;
import com.mukul.assignment.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mukul on 6/28/16.
 */
@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
}
