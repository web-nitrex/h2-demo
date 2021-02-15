package com.example.h2demo.models;

public class Genre {
    private long id;
    private String nameGenre;

    public Genre() {
    }

    public Genre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", nameGenre='" + nameGenre + '\'' +
                '}';
    }
}
