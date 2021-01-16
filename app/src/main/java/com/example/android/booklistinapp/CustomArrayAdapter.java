package com.example.android.booklistinapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class CustomArrayAdapter extends ArrayAdapter {
    public static final String LOG_TAG = CustomArrayAdapter.class.getName();
    public CustomArrayAdapter(@NonNull Context context, ArrayList<BookDetail> arrayList) {
        super(context, 0, arrayList);
    }
    private TextView textView;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        BookDetail placeholder = (BookDetail) getItem(position);

        //setting the book image using picasso
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.book_image);
        Log.e(LOG_TAG, placeholder.getImageLink());

        Glide.with(this.getContext()).load("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png").into(imageView);

        //setting the book title
        TextView title_text = (TextView) listItemView.findViewById(R.id.book_name);
        title_text.setText(placeholder.getTitle());


        //setting the author
        textView = (TextView) listItemView.findViewById(R.id.author_name);
        textView.setText(placeholder.getAuthors());

        //setting the publisher
        textView = (TextView) listItemView.findViewById(R.id.publisher);
        textView.setText(placeholder.getPublisher());

        //setting the description
        textView = (TextView) listItemView.findViewById(R.id.description);
        textView.setText(placeholder.getDescription());
        textView.setMovementMethod(new ScrollingMovementMethod());

        return listItemView;
    }
}
