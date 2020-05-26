package com.mapps.pixabayclientapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mapps.pixabayclientapp.adapters.MainRecyclerAdapter;
import com.mapps.pixabayclientapp.R;
import com.mapps.pixabayclientapp.models.Image;
import com.mapps.pixabayclientapp.ui.details.DetailsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainRecyclerAdapter.ItemClickListener {

    private static final int COLUMNS_NUMBER = 5;

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String WALLPAPER_ID = "wallpaper_id";
    public static final String WALLPAPER_NAME = "wallpaper_name";

    private TextView tv;
    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private MainRecyclerAdapter recyclerAdapter;
    private int currentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setSupportActionBar(toolbar);

        recyclerAdapter = new MainRecyclerAdapter(this,this);
        final GridLayoutManager mLayoutManager =  new GridLayoutManager(getBaseContext(), COLUMNS_NUMBER);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        final MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.setMessage("This is a test");

        mainViewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tv.setText(s);
            }
        });

        mainViewModel.getCurrentPage().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer page) {
                currentPage = page;
            }
        });

        mainViewModel.getImages().observe(this, new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> images) {
                recyclerAdapter.setData(images);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mainViewModel.incrementPage();
                    mainViewModel.addData(currentPage);
                    Log.d(TAG, "Current Page: " + currentPage);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });

    }

    private void initViews() {
        toolbar = findViewById(R.id.main_toolbar);
        tv = findViewById(R.id.message);
        recyclerView = findViewById(R.id.main_recycler_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
        intent.putExtra(WALLPAPER_ID, recyclerAdapter.mData.get(position).getId());
        intent.putExtra(WALLPAPER_NAME, recyclerAdapter.mData.get(position).getUser());
        startActivity(intent);
    }
}
