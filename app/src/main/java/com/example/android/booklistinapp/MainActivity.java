package com.example.android.booklistinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Retrieving text from EditText fields
                EditText editText = (EditText) findViewById(R.id.search);
                String searched_text = editText.getText().toString();
                Log.i(LOG_TAG, "Searched Book: "+searched_text);

                if(!searched_text.isEmpty())
                {
                    Intent intent = new Intent(MainActivity.this, OnSubmitActivity.class);
                    intent.putExtra("BOOK_KEY" , searched_text);
                    startActivity(intent);
                }
                else
                {
                    Log.e(LOG_TAG, "making Toast");
                    Toast.makeText(getBaseContext(), "Enter Something to Search", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}