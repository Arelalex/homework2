package com.dmdev.java_core.oop.collection.hw3.cinema;

import com.dmdev.java_core.oop.collection.hw3.genre.Genre;

import java.util.*;

public class Cinema {

    private final Map<Integer, LinkedHashSet<Film>> map;

    public Cinema() {
        map = new HashMap<>();
    }

    public void addFilm(Film film) {
        if (map.containsKey(film.getYear())) {
            map.get(film.getYear()).add(film);
        } else {
            map.put(film.getYear(), new LinkedHashSet<>());
            map.get(film.getYear()).add(film);
        }
    }

    public Set<Film> getFilmYear(Integer year) {
        return this.map.get(year);
    }

    public Set<Film> getFilmYearAndMonth(Integer year, Integer month) {
        Set<Film> newListFilm = new LinkedHashSet<>();
        for (Film value : map.get(year)) {
            if (value.getMonth().equals(month)) {
                newListFilm.add(value);
            }
        }
        return newListFilm;
    }

    public Set<Film> getFilmGenre(Genre genre) {
        Set<Film> newSetFilm = new LinkedHashSet<>();
        for (Set<Film> value : map.values()) {
            for (Film valueFilm : value) {
                if (valueFilm.getGenre().equals(genre)) {
                    newSetFilm.add(valueFilm);
                }
            }
        }
        return newSetFilm;
    }

    public List<Film> getTop10Film() {
        List<Film> newSetFilm = new ArrayList<>();
        for (Set<Film> value : map.values()) {
            newSetFilm.addAll(value);
        }
        Collections.sort(newSetFilm);
        if (newSetFilm.size() >= 10) {
            return newSetFilm.subList(0, 10);
        } else {
            return newSetFilm.subList(0, newSetFilm.size());
        }
    }

    public Map<Integer, LinkedHashSet<Film>> getMap() {
        return map;
    }
}
