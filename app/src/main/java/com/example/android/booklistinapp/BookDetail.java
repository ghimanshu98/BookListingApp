package com.example.android.booklistinapp;

public class BookDetail
{
    private String title, authors, publisher, description;
    private int ratingCount;
    private String imageLink;

    public BookDetail(String title, String authors, String publisher, String description, int ratingCount, String imageLink)
    {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.description = description;
        this.ratingCount = ratingCount;
        this.imageLink = imageLink;
    }

    public String getTitle(String title) {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public String getImageLink() {
        return imageLink;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", ratingCount=" + ratingCount +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
