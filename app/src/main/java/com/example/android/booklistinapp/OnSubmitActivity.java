package com.example.android.booklistinapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;

public class OnSubmitActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<BookDetail>>
{
    private String url = "https://www.googleapis.com/books/v1/volumes?q=";

    public static String LOG_TAG = OnSubmitActivity.class.getName();

    private ProgressBar progressBar;
    private TextView emptyTextView;

    private CustomArrayAdapter adapter;

    private String searched_text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        //Setting the toolbar on the onSubmitActivity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list);
        setSupportActionBar(toolbar);
        //adding back button functionality
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //Setting the progressbar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        //Setting the emptyTextView, here the textView does not contain any text
        emptyTextView = (TextView) findViewById(R.id.emptyTextView);

        //Processing the intent received and extracting the data from it
        Intent intent = getIntent();
        searched_text = intent.getStringExtra("BOOK_KEY");
        //Log.i(LOG_TAG, "Searched Book received int Intent: "+searched_text);
        url = url + searched_text + "&maxResults=20";
        Log.e(LOG_TAG, "url: " + url);

        //Checking if internet is active on device by using the helper method isOnline()
        if(isOnline())
        {
            //Empty ArrayList
            getSupportLoaderManager().initLoader(1, null, this).forceLoad();

            //Getting a reference of ListView  to set Adapter
            ListView listView = (ListView) findViewById(R.id.listview);

            //setting the emptyTextView on ListView
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
        else    //if internet is not active
        {
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText("No Internet Available!!\nCheck Your Internet Connection.");
        }

        //setting refresh button in toolbar and creating intent on pressed
        Button refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnSubmitActivity.this, OnSubmitActivity.class);
                intent.putExtra("BOOK_KEY", searched_text);
                // intent to new activity
                startActivity(intent);
                //Closing the last activity
                finish();
            }
        });
    }

    @NonNull
    @Override
    public Loader<ArrayList<BookDetail>> onCreateLoader(int id, @Nullable Bundle args) {
        //Creates a loader object if not already exists and informs the loader manager who further
        // calls the loadInBackground()
        return new CustomLoader(this, url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<BookDetail>> loader, ArrayList<BookDetail> data)
    {
        //Runs when loadinBackgound() is finished
        progressBar.setVisibility(View.GONE);
        emptyTextView.setText("Sorry!! Got No Books to Show :(");
        adapter.clear();
        if(data != null && !data.isEmpty())
        {
            //adding data in adapter
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