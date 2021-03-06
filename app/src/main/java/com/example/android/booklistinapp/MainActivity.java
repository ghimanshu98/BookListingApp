package com.example.android.booklistinapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting the toolbar on the MainActivityPage
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Adding reference of Submit Button and setting onClickListener
        Button button = (Button) findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieving text from EditText fields
                EditText editText = (EditText) findViewById(R.id.search);
                String searched_text = editText.getText().toString();
                //Log.i(LOG_TAG, "Searched Book: "+searched_text);

                //if retrieved text is not empty then send the intent to onSubmitActivity
                if(!searched_text.isEmpty())
                {
                    Intent intent = new Intent(MainActivity.this, OnSubmitActivity.class);
                    //Putting the searchedText with the intent
                    intent.putExtra("BOOK_KEY" , searched_text);
                    startActivity(intent);
                }
                else // Create a toast message
                {
                    //Log.e(LOG_TAG, "making Toast");
                    Toast.makeText(getBaseContext(), "Enter Something to Search", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}