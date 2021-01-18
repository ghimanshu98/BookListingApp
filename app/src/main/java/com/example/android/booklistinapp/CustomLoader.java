package com.example.android.booklistinapp;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class CustomLoader extends AsyncTaskLoader<ArrayList<BookDetail>>
{
    private static final String LOG_TAG =  CustomLoader.class.getName();
    private final String url;
    /**
     * Constructor for creating object of {@link CustomLoader}
     * @param context current context of view
     */
    public CustomLoader(Context context, String url)
    {
        super(context);
        this.url = url;
    }

    /**
     *  Actually triggers the loadInBackground() when called from onCreate() from EarthquakeActivity.
     */
    @Override
    protected void onStartLoading() {
        //Log.i(LOG_TAG, "inside Loader startLoading()");
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<BookDetail> loadInBackground() {
        if(url == null)
        {
            return null;
        }
        return Utils.fetchDataFromUrl(url);
    }
}
