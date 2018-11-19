package com.mukul.assignment.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mukul.assignment.R;
import com.mukul.assignment.home.tabs.ViewPagerAdapter;
import com.mukul.assignment.models.PostList;
import com.mukul.assignment.viewmodels.PostListViewModel;

import java.util.List;

public class HomeActivityNew extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private Toolbar mTopToolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TextView tvName, tvDesignation, tvLocation, tvQualification, tvExperience, tvCtc, tvWorking, tvRole;
    ImageView tvProfileImage, tvLocationImage;

    ProgressBar progressBar;
    private PostListViewModel postListViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        renderView();
        init();

        postListViewModel = ViewModelProviders.of(HomeActivityNew.this).get(PostListViewModel.class);
        postListViewModel.loadPosListLiveData(HomeActivityNew.this);
        postListViewModel.getPosListLiveData().observe(HomeActivityNew.this, postLists -> updateUI(postLists));

    }

    public  void renderView(){
        setContentView(R.layout.activity_home_new);
        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        progressBar = findViewById(R.id.progress);
        tvName = findViewById(R.id.tv_name);
        tvDesignation = findViewById(R.id.tv_designation);
        tvProfileImage = findViewById(R.id.tv_profile_image);
        tvLocationImage = findViewById(R.id.tv_location_image);
        tvLocation = findViewById(R.id.tv_location);
        tvQualification = findViewById(R.id.tv_qualification);
        tvExperience = findViewById(R.id.tv_experience);
        tvCtc = findViewById(R.id.tv_ctc);
        tvWorking = findViewById(R.id.tv_cwork1);
        tvRole = findViewById(R.id.tv_desig1);
    }

    public void init(){

        //toolbar

        setSupportActionBar(mTopToolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        mTopToolbar.setNavigationIcon(R.drawable.menu);

        //Adding the tabs using addTab() method

        tabLayout.setTabIndicatorFullWidth(false);
        tabLayout.addTab(tabLayout.newTab().setText("SKILL SET"));
        tabLayout.addTab(tabLayout.newTab().setText("WORK FUNCTION"));
        tabLayout.addTab(tabLayout.newTab().setText("INDUSTRY"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        //Initializing view pager adapter
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        //Adding adapter to pager
        viewPager.setAdapter(viewPagerAdapter);
        //Adding onTabSelectedListener to swipe views
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        progressBar.setVisibility(View.VISIBLE);

    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateUI(@Nullable List<PostList> postLists){

        progressBar.setVisibility(View.GONE);

        if (postLists != null){
            PostList postListResponse = postLists.get(0);
            tvName.setText(postListResponse.getData().getName());
            tvDesignation.setText(postListResponse.getData().getDesignation().getName());

            String images = postListResponse.getData().getImage();

            Glide.with(HomeActivityNew.this)
                    .load(images)
                    .into(tvProfileImage);
            tvLocation.setText(postListResponse.getData().getLocation());
            tvQualification.setText(postListResponse.getData().getHighestQualification().getName());
            tvExperience.setText(postListResponse.getData().getExperience());
            tvCtc.setText(postListResponse.getData().getExpectedCtc());
            tvWorking.setText(postListResponse.getData().getLastCompany().getName());
            tvRole.setText(postListResponse.getData().getDesignation().getName());
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Toast.makeText(HomeActivityNew.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }
    public void showMessage(View view) {
        displayToast(getString(R.string.message_hand));
    }
    public void showMessage1(View view) {
        displayToast(getString(R.string.message_cross));
    }
    public void showMessage2(View view) {
        displayToast(getString(R.string.message_like));
    }


}
