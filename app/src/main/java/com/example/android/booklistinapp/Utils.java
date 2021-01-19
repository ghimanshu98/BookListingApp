package com.example.android.booklistinapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Utils {
    public static String LOG_TAG = Utils.class.getName();

    public static ArrayList<BookDetail> fetchDataFromUrl(String urlString)
    {

        URL url = createURL(urlString);
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpsRequest(url);
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        ArrayList<BookDetail> temp = fetchDataFromJsonString(jsonResponse);
        return temp;
    }


    private static URL createURL(String urlString)
    {
        try
        {
            return new URL(urlString);
        }catch (MalformedURLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static String makeHttpsRequest(URL url) throws IOException
    {
        if(url == null)
        {
            return null;
        }
        HttpsURLConnection httpsURLConnection = null;
        InputStream inputStream = null;
        String jsonResponse = null;
        try
        {
            //Opening connection
            httpsURLConnection = (HttpsURLConnection) url.openConnection();

            //Setting properties of Connection
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setConnectTimeout(15000);
            httpsURLConnection.setReadTimeout(10000);

            //Establishing connection
            if(httpsURLConnection.getResponseCode() == 200)
            {
                httpsURLConnection.connect();
                inputStream = httpsURLConnection.getInputStream();
                jsonResponse = getDataFromStream(inputStream);
            }
            else
            {
                Log.e(LOG_TAG, "Connection unsuccessful "+httpsURLConnection.getResponseCode());
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            if(inputStream!=null)
            {
                inputStream.close();
            }
            if(httpsURLConnection != null)
                httpsURLConnection.disconnect();
        }
        return jsonResponse;
    }

    private static String getDataFromStream(InputStream inputStream) throws IOException
    {
        if(inputStream == null)
        {
            return null;
        }

        StringBuilder output = new StringBuilder();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        while(line != null)
        {
            output.append(line);
            line = bufferedReader.readLine();
        }

        return output.toString();
    }

    private static ArrayList<BookDetail> fetchDataFromJsonString(String jsonString)
    {
        ArrayList<BookDetail> bookList = new ArrayList<BookDetail>();
        if(!jsonString.isEmpty() || jsonString != null) {
            try {
                JSONObject root = new JSONObject(jsonString);
                //Parsing the json

                JSONArray items = root.optJSONArray("items");
                if (items != null) {
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject placeholder = items.optJSONObject(i);

                        JSONObject volumeInfo = placeholder.optJSONObject("volumeInfo");

                        if (volumeInfo != null) {

                            String title = "Not Found";
                            if (volumeInfo.has("title")) {
                                title = volumeInfo.optString("title");
                            }

                            JSONArray authors = null;
                            if (volumeInfo.has("authors")) {
                                authors = volumeInfo.optJSONArray("authors");
                            }
                            StringBuilder str = null;
                            String author = null;
                            if (authors != null) {
                                str = new StringBuilder(authors.optString(0));
                                for (int j = 1; j < authors.length(); j++) {
                                    str.append(", ");
                                    str.append(authors.optString(j));
                                }
                                author = str.toString();
                                Log.i(LOG_TAG, author);
                            } else {
                                str = new StringBuilder("Not Found");
                                author = str.toString();
                            }

                            String publisher = "Not Found";
                            if (volumeInfo.has("publisher")) {
                                publisher = volumeInfo.optString("publisher");
                            }
                            //String description = volumeInfo.optString("description");

                            double rating = 0.0;
                            if (volumeInfo.has("averageRating")) {
                                rating = volumeInfo.optDouble("averageRating");
                            }

                            JSONObject imageLinks = null;
                            String imageLink = "Not Found";
                            if (volumeInfo.has("imageLinks")) {
                                imageLinks = volumeInfo.optJSONObject("imageLinks");
                            }
                            if (imageLinks != null) {
                                if (imageLinks.has("thumbnail")) {
                                    imageLink = imageLinks.optString("thumbnail");
                                }
                            }

                            String previewLink = "Not Found";
                            if (volumeInfo.has("previewLink")) {
                                previewLink = volumeInfo.optString("previewLink");
                            }
                            bookList.add(new BookDetail(title, author, publisher, rating, imageLink, previewLink));
                            //Log.i(LOG_TAG, bookList.get(i).toString());
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Log.i(LOG_TAG, "Size: "+bookList.size());
        return bookList;
    }
}
