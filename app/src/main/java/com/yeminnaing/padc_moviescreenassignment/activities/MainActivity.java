package com.yeminnaing.padc_moviescreenassignment.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.yeminnaing.padc_moviescreenassignment.R;
import com.yeminnaing.padc_moviescreenassignment.adapters.MovieListAdapter;
import com.yeminnaing.padc_moviescreenassignment.adapters.MoviePagerAdapter;
import com.yeminnaing.padc_moviescreenassignment.data.vo.MoviesVO;
import com.yeminnaing.padc_moviescreenassignment.delegates.MovieItemDelegate;
import com.yeminnaing.padc_moviescreenassignment.fragments.MovieFragment;
import com.yeminnaing.padc_moviescreenassignment.persistance.Contract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MovieItemDelegate, NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.pager_for_movies)
    ViewPager pagerMovies;

    @BindView(R.id.tabLayout)
    TabLayout tlMovies;

    private MoviePagerAdapter mMoviePagerAdapter;
    private MovieListAdapter mMovieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this, this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Movie Shelf");
        }

        mMoviePagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());
        mMoviePagerAdapter.addTab(MovieFragment.newInstance(), getString(R.string.now_playing_movies));
        mMoviePagerAdapter.addTab(MovieFragment.newInstance(), getString(R.string.upcoming_movies));
        mMoviePagerAdapter.addTab(MovieFragment.newInstance(), getString(R.string.most_popular_movies));

        pagerMovies.setAdapter(mMoviePagerAdapter);
        pagerMovies.setOffscreenPageLimit(mMoviePagerAdapter.getCount());
        tlMovies.setupWithViewPager(pagerMovies);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTapMovieOverview(MoviesVO movie) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
       return new CursorLoader(getApplicationContext(),
                Contract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (data != null && data.moveToFirst()) {
            List<MoviesVO> movieList = new ArrayList<>();

            do {
                MoviesVO movie = MoviesVO.parseFromCursor(getApplicationContext(), data);
                movieList.add(movie);
            } while (data.moveToNext());

            mMovieListAdapter.setNewData(movieList);

        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
