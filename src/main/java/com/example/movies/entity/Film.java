package com.example.movies.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "id")
    private Set<FilmGenre> FilmsGender;

    @Column()
    private String title;

    @Column()
    private String country;

    @Column()
    private int date;

    @Column()
    private int year;

    @Column()
    private boolean rRated;

    @Column()
    private int rating;

    @Column()
    private int numOfRatings;

    @Column()
    private int length;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<FilmGenre> getFilmsGender() {
        return FilmsGender;
    }

    public void setFilmsGender(Set<FilmGenre> filmsGender) {
        FilmsGender = filmsGender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isrRated() {
        return rRated;
    }

    public void setrRated(boolean rRated) {
        this.rRated = rRated;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
