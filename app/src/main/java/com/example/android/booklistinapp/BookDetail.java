package com.example.android.booklistinapp;

import android.util.Log;

public class BookDetail
{
    public static final String LOG_TAG = BookDetail.class.getName();
    private String title, authors, publisher;//, description;
    private double rating;
    private String imageLink;
    private String bookPreviewLink;

    public BookDetail(String title, String authors, String publisher, /*String description, */double rating, String imageLink, String bookPreviewLink)
    {
        Log.i(LOG_TAG, "INSIDE BookDetail Constructor");
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        //this.description = description;
        this.rating = rating;
        this.imageLink = imageLink;
        this.bookPreviewLink = bookPreviewLink;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

//    public String getDescription() {
//        return description;
//    }

    public double getRating() {
        return rating;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getBookPreviewLink() {
        return bookPreviewLink;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
               // ", description='" + description + '\'' +
                ", rating=" + rating +
                ", imageLink='" + imageLink + '\'' +
                ", bookPreviewLink='" + bookPreviewLink + '\'' +
                '}';
    }
}
