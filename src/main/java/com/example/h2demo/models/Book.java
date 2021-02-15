package com.example.h2demo.models;

public class Book {

    private long id;
    private String name;
    private int numberOfPages;
    private long authorId;
    private long genreId;

    public Book() {
    }

    public Book(String name, int numberOfPages, long authorId, long genreId) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", authorId=" + authorId +
                ", genreId=" + genreId +
                '}';
    }
}
