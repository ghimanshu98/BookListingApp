package com.example.android.booklistinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OnSubmitActivity extends AppCompatActivity {

    public static String LOG_TAG = OnSubmitActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        //HardCoded String url for testing the app
        String jsonString = "{\n" +
                "  \"kind\": \"books#volumes\",\n" +
                "  \"totalItems\": 1672,\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"hlygR8lJTzUC\",\n" +
                "      \"etag\": \"PtU4UYfoKgM\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/hlygR8lJTzUC\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Beginning Android Application Development\",\n" +
                "        \"authors\": [\n" +
                "          \"Wei-Ming Lee\"\n" +
                "        ],\n" +
                "        \"publisher\": \"John Wiley & Sons\",\n" +
                "        \"publishedDate\": \"2011-03-10\",\n" +
                "        \"description\": \"Create must-have applications for the latest Android OS The Android OS is a popular and flexible platform for many of today's most in-demand mobile devices. This full-color guide offers you a hands-on introduction to creating Android applications for the latest mobile devices. Veteran author Wei Meng Lee accompanies each lesson with real-world examples to drive home the content he covers. Beginning with an overview of core Android features and tools, he moves at a steady pace while teaching everything you need to know to successfully develop your own Android applications. Explains what an activity is and reviews its lifecycle Zeroes in on customizing activities by applying styles and themes Looks at the components of a screen, including LinearLayout, AbsoluteLayout, and RelativeLayout, among others Details ways to adapt to different screen sizes and adjust display orientation Reviews the variety of views such as TextView, ProgressBar, TimePicker, and more Beginning Android Application Development pares down the most essential steps you need to know so you can start creating Android applications today.\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"1118087801\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9781118087800\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": true,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 448,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Computers\"\n" +
                "        ],\n" +
                "        \"averageRating\": 2.5,\n" +
                "        \"ratingsCount\": 4,\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": true,\n" +
                "        \"contentVersion\": \"2.10.6.0.preview.3\",\n" +
                "        \"panelizationSummary\": {\n" +
                "          \"containsEpubBubbles\": false,\n" +
                "          \"containsImageBubbles\": false\n" +
                "        },\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=hlygR8lJTzUC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=hlygR8lJTzUC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"en\",\n" +
                "        \"previewLink\": \"http://books.google.co.in/books?id=hlygR8lJTzUC&pg=PA2&dq=android&hl=&cd=1&source=gbs_api\",\n" +
                "        \"infoLink\": \"https://play.google.com/store/books/details?id=hlygR8lJTzUC&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://play.google.com/store/books/details?id=hlygR8lJTzUC\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"IN\",\n" +
                "        \"saleability\": \"FOR_SALE\",\n" +
                "        \"isEbook\": true,\n" +
                "        \"listPrice\": {\n" +
                "          \"amount\": 588.82,\n" +
                "          \"currencyCode\": \"INR\"\n" +
                "        },\n" +
                "        \"retailPrice\": {\n" +
                "          \"amount\": 294.41,\n" +
                "          \"currencyCode\": \"INR\"\n" +
                "        },\n" +
                "        \"buyLink\": \"https://play.google.com/store/books/details?id=hlygR8lJTzUC&rdid=book-hlygR8lJTzUC&rdot=1&source=gbs_api\",\n" +
                "        \"offers\": [\n" +
                "          {\n" +
                "            \"finskyOfferType\": 1,\n" +
                "            \"listPrice\": {\n" +
                "              \"amountInMicros\": 588820000,\n" +
                "              \"currencyCode\": \"INR\"\n" +
                "            },\n" +
                "            \"retailPrice\": {\n" +
                "              \"amountInMicros\": 294410000,\n" +
                "              \"currencyCode\": \"INR\"\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"IN\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": true,\n" +
                "          \"acsTokenLink\": \"http://books.google.co.in/books/download/Beginning_Android_Application_Developmen-sample-epub.acsm?id=hlygR8lJTzUC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": true,\n" +
                "          \"acsTokenLink\": \"http://books.google.co.in/books/download/Beginning_Android_Application_Developmen-sample-pdf.acsm?id=hlygR8lJTzUC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=hlygR8lJTzUC&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"\\u003cb\\u003eAndroid\\u003c/b\\u003e is a mobile operating system that is based on a modified version of Linux\\u003cbr\\u003e\\n. It was originally developed by a startup of the same name, \\u003cb\\u003eAndroid\\u003c/b\\u003e, Inc. In 2005, \\u003cbr\\u003e\\nas part of its strategy to enter the mobile space, Google purchased \\u003cb\\u003eAndroid\\u003c/b\\u003e and&nbsp;...\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"kind\": \"books#volume\",\n" +
                "      \"id\": \"5BGBswAQSiEC\",\n" +
                "      \"etag\": \"/b9lcReZUBQ\",\n" +
                "      \"selfLink\": \"https://www.googleapis.com/books/v1/volumes/5BGBswAQSiEC\",\n" +
                "      \"volumeInfo\": {\n" +
                "        \"title\": \"Programming Android\",\n" +
                "        \"authors\": [\n" +
                "          \"Zigurd Mednieks\",\n" +
                "          \"Laird Dornin\",\n" +
                "          \"Blake Meike\",\n" +
                "          \"Masumi Nakamura\"\n" +
                "        ],\n" +
                "        \"publisher\": \"\\\"O'Reilly Media, Inc.\\\"\",\n" +
                "        \"publishedDate\": \"2011-07-22\",\n" +
                "        \"description\": \"Presents instructions for creating Android applications for mobile devices using Java.\",\n" +
                "        \"industryIdentifiers\": [\n" +
                "          {\n" +
                "            \"type\": \"ISBN_13\",\n" +
                "            \"identifier\": \"9781449389697\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"type\": \"ISBN_10\",\n" +
                "            \"identifier\": \"1449389694\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"readingModes\": {\n" +
                "          \"text\": false,\n" +
                "          \"image\": true\n" +
                "        },\n" +
                "        \"pageCount\": 482,\n" +
                "        \"printType\": \"BOOK\",\n" +
                "        \"categories\": [\n" +
                "          \"Computers\"\n" +
                "        ],\n" +
                "        \"averageRating\": 3.5,\n" +
                "        \"ratingsCount\": 2,\n" +
                "        \"maturityRating\": \"NOT_MATURE\",\n" +
                "        \"allowAnonLogging\": true,\n" +
                "        \"contentVersion\": \"0.3.0.0.preview.1\",\n" +
                "        \"imageLinks\": {\n" +
                "          \"smallThumbnail\": \"http://books.google.com/books/content?id=5BGBswAQSiEC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\n" +
                "          \"thumbnail\": \"http://books.google.com/books/content?id=5BGBswAQSiEC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"\n" +
                "        },\n" +
                "        \"language\": \"en\",\n" +
                "        \"previewLink\": \"http://books.google.co.in/books?id=5BGBswAQSiEC&pg=PA431&dq=android&hl=&cd=2&source=gbs_api\",\n" +
                "        \"infoLink\": \"http://books.google.co.in/books?id=5BGBswAQSiEC&dq=android&hl=&source=gbs_api\",\n" +
                "        \"canonicalVolumeLink\": \"https://books.google.com/books/about/Programming_Android.html?hl=&id=5BGBswAQSiEC\"\n" +
                "      },\n" +
                "      \"saleInfo\": {\n" +
                "        \"country\": \"IN\",\n" +
                "        \"saleability\": \"NOT_FOR_SALE\",\n" +
                "        \"isEbook\": false\n" +
                "      },\n" +
                "      \"accessInfo\": {\n" +
                "        \"country\": \"IN\",\n" +
                "        \"viewability\": \"PARTIAL\",\n" +
                "        \"embeddable\": true,\n" +
                "        \"publicDomain\": false,\n" +
                "        \"textToSpeechPermission\": \"ALLOWED\",\n" +
                "        \"epub\": {\n" +
                "          \"isAvailable\": false\n" +
                "        },\n" +
                "        \"pdf\": {\n" +
                "          \"isAvailable\": true\n" +
                "        },\n" +
                "        \"webReaderLink\": \"http://play.google.com/books/reader?id=5BGBswAQSiEC&hl=&printsec=frontcover&source=gbs_api\",\n" +
                "        \"accessViewStatus\": \"SAMPLE\",\n" +
                "        \"quoteSharingAllowed\": false\n" +
                "      },\n" +
                "      \"searchInfo\": {\n" +
                "        \"textSnippet\": \"... that enables the necessary interactions for making and managing connections \\u003cbr\\u003e\\nand for using applications that communicate over Bluetooth. \\u003cb\\u003eAndroid\\u003c/b\\u003e uses the \\u003cbr\\u003e\\nBluez Bluetooth stack, which is the most commonly used Bluetooth stack for \\u003cbr\\u003e\\nLinux.\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        ArrayList<BookDetail> bookList = new ArrayList<>();

        //From this json string create a json object
        try {
            JSONObject root = new JSONObject(jsonString);
            //Parsing the json

            JSONArray items = root.optJSONArray("items");
            for(int i=0; i < items.length(); i++)
            {
                JSONObject placeholder = items.optJSONObject(i);
                JSONObject volumeInfo = placeholder.optJSONObject("volumeInfo");

                String title = volumeInfo.optString("title");

                JSONArray authors = volumeInfo.optJSONArray("authors");
                StringBuilder str = new StringBuilder(authors.optString(0));
                for(int j=1; j<authors.length(); j++)
                {
                    str.append("\n");
                    str.append(authors.optString(j));
                }
                String author = str.toString();
                Log.i(LOG_TAG, author);

                String publisher = volumeInfo.optString("publisher");
                String description = volumeInfo.optString("description");

                double rating = volumeInfo.optDouble("averageRating");

                JSONObject imageLinks = volumeInfo.optJSONObject("imageLinks");
                String imageLink = imageLinks.optString("thumbnail");

                String previewLink = volumeInfo.optString("previewLink");

                bookList.add(new BookDetail(title, author, publisher, description, rating, imageLink, previewLink));
                Log.i(LOG_TAG, bookList.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Getting a reference of ListView  to set Adapter
        ListView listView = (ListView) findViewById(R.id.listview);

        //Creating object of Custom Array Adapter;
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, bookList);

        //setting adapter on listView
        listView.setAdapter(adapter);


    }
}