package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RockFragment.OnFragmentInteractionListener {

    TabLayout tabLayout;
    TabItem tab_rock, tab_classic, tab_pop;
    ViewPager viewPager;
    RockFragment rockFragment;

    PageAdapter pageAdapter;

    RecyclerView recyclerView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        tab_rock = findViewById(R.id.tab_rock);
        tab_classic = findViewById(R.id.tab_classic);
        tab_pop = findViewById(R.id.tab_pop);
        viewPager = findViewById(R.id.viewPager);
        pageAdapter = new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void retrieveSongList(final String genre){

        getApiInterface().getSongs(genre,"music","song",50).enqueue(new Callback<ResponsePojo>() {
            @Override
            public void onResponse(Call<ResponsePojo> call, Response<ResponsePojo> response) {
                if(response.isSuccessful()){
                    switch (genre){
                        case "rock":
                            getRockListing(response);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePojo> call, Throwable t) {

            }
        });

    }

    private ApiInterface getApiInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiInterface.class);
    }

    public void getRockListing(Response response) {
        recyclerView = findViewById(R.id.rockRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        adapter = new CustomAdapter();
        adapter.setDataSet(response.body());
        adapter.setListener(MainActivity.this);
        recyclerView.setAdapter(adapter);

    }
}
