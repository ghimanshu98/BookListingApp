package com.example.android.booklistinapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
    public static final String LOG_TAG = ImageLoadTask.class.getName();

    private String url = null;
    private ImageView imageView = null;

    public ImageLoadTask(String url, ImageView imageView)
    {
        this.url = url;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... voids)  {
        InputStream inputStream= null;
        Bitmap bitmap = null;
        HttpURLConnection httpURLConnection = null;
        try{
            URL urlConn = new URL(url);
            Log.i(LOG_TAG, "url"+urlConn);
            httpURLConnection = (HttpURLConnection) urlConn.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            Log.i(LOG_TAG, "response code: "+httpURLConnection.getResponseCode());


            inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

            inputStream.close();
            httpURLConnection.disconnect();
            return bitmap;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap!= null)
        {
            imageView.setImageBitmap(bitmap);
        }
        else
        {
            imageView.setImageResource(R.drawable.place_holder_image);
        }
    }
}
