package com.mukul.assignment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mukul.assignment.deps.DaggerDeps;
import com.mukul.assignment.deps.Deps;
import com.mukul.assignment.networking.NetworkModule;

import java.io.File;

/**
 * Created by mukul on 6/28/16.
 */
public class BaseApp  extends AppCompatActivity{
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }
}
