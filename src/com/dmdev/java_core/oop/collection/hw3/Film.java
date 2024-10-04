package com.dmdev.java_core.oop.collection.hw3;

import com.dmdev.java_core.oop.collection.hw3.genre.Genre;

import java.util.Objects;

public class Film implements Comparable<Film>{
    private Integer id;
    private Integer year;
    private Integer month;
    private Genre genre;
    private Double rating;

    public Film(Integer id, Integer year, Integer month, Genre genre, Double rating) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(id, film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Genre getGenre() {
        return genre;
    }

    public Double getRating() {
        return rating;
    }

    @Override
    public int compareTo(Film o) {
        return o.rating.compareTo(rating);
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                '}';
    }
}
