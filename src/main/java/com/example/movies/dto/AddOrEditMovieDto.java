package com.example.movies.dto;


import com.example.movies.entity.Actor;

import java.util.List;

public class AddOrEditMovieDto {

    private long id;

    private String title;

    private int year;

    private int length;

    private List<Actor> filmActors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Actor> getFilmActors() {
        return filmActors;
    }

    public void setFilmActors(List<Actor> filmActors) {
        this.filmActors = filmActors;
    }
}
