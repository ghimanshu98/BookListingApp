package com.example.android.booklistinapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.net.NetworkInterface;
import java.util.ArrayList;

public class OnSubmitActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<BookDetail>>
{
    private String url = "https://www.googleapis.com/books/v1/volumes?q=";

    public static String LOG_TAG = OnSubmitActivity.class.getName();

    private ProgressBar progressBar;
    private TextView emptyTextView;

    private CustomArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        emptyTextView = (TextView) findViewById(R.id.emptyTextView);

        if(isOnline())
        {
            Intent intent = getIntent();
            String searched_text = intent.getStringExtra("BOOK_KEY");
            //Log.i(LOG_TAG, "Searched Book received int Intent: "+searched_text);
            url = url + searched_text + "&maxResults=20";
            Log.e(LOG_TAG, "url: " + url);

            //Empty ArrayList
            getSupportLoaderManager().initLoader(1, null, this).forceLoad();

            //Getting a reference of ListView  to set Adapter
            ListView listView = (ListView) findViewById(R.id.listview);

            listView.setEmptyView(emptyTextView);

            //Creating object of Custom Array Adapter;
            adapter = new CustomArrayAdapter(this, new ArrayList<BookDetail>()); //passing an empty arrayList

            //setting adapter on listView
            listView.setAdapter(adapter);

            //ItemListener for opening bookPreview Links
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BookDetail temp = (BookDetail) adapter.getItem(position);
                    Uri uri = Uri.parse(temp.getBookPreviewLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
        }
        else
        {
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText("No Internet Available!!\nCheck Your Internet Connection.");
        }

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = new Intent(OnSubmitActivity.this, OnSubmitActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @NonNull
    @Override
    public Loader<ArrayList<BookDetail>> onCreateLoader(int id, @Nullable Bundle args) {
        return new CustomLoader(this, url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<BookDetail>> loader, ArrayList<BookDetail> data)
    {
        progressBar.setVisibility(View.GONE);
        emptyTextView.setText("Sorry!! Got No Books to Show :(");
        adapter.clear();
        if(data != null && !data.isEmpty())
        {
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<BookDetail>> loader) {
        adapter.clear();
    }

    public boolean isOnline()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!= null && networkInfo.isConnected())
        {
            Log.i(LOG_TAG, "Internet_Connected "+"True");
            return true;
        }
        else
        {
            Log.i(LOG_TAG, "Internet_Connected "+"False");
            return false;
        }
    }
}