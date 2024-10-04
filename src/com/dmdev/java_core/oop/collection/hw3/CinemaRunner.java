package com.dmdev.java_core.oop.collection.hw3;

import java.util.LinkedHashSet;
import java.util.Map;

import static com.dmdev.java_core.oop.collection.hw3.genre.Genre.*;

/**
 * Даны 2 класса:
 * - Фильм с 5-ю полями: Уникальный Целочисленный идентификатор (id), Год издания, Месяц издания, Жанр и Рейтинг;
 * - Кинотеатр, где есть всего лишь одно единственное поле: отсортированный ассоциативный массив, где ключом является
 * год издания, а значением - все фильмы, выпустившиеся в этом году.
 * Добавить функционал в кинотеатр таким образом, чтобы можно было:
 * - добавлять в него новый фильм
 * - получить все фильмы по переданному году
 * - получить все фильмы по переданному году и месяцу
 * - получить все фильмы по переданному жанру
 * - получать ТОП-10 фильмов отсортированные по Рейтингу в порядке убывания
 * Учесть следующее:
 * - в кинотеатре фильмы должны храниться в порядке их добавления в него (т.е. предусмотреть порядок значения ассоциативного массива)
 * - не должен добавляться фильм, если такой уже есть в кинотеатре
 * Продемонстрировать работу кинотеатра в отдельном классе.
 */
public class CinemaRunner {
    public static void main(String[] args) {

        Film film1 = new Film(1, 2001, 1, COMEDY, 7.8);
        Film film2 = new Film(2, 2001, 2, DOCUMENTARY, 5.0);

        Film film3 = new Film(3, 2002, 3, HORROR, 6.7);
        Film film4 = new Film(4, 2002, 7, THRILLER, 5.6);
        Film film5 = new Film(5, 2002, 6, DOCUMENTARY, 4.2);
        Film film6 = new Film(6, 2002, 7, COMEDY, 4.2);

        Film film7 = new Film(7, 2003, 10, HORROR, 3.5);
        Film film8 = new Film(8, 2003, 12, COMEDY, 9.0);
        Film film9 = new Film(9, 2003, 7, COMEDY, 1.5);
        Film film10 = new Film(10, 2003, 2, COMEDY, 1.7);

        Cinema cinema = new Cinema();
        cinema.addFilm(film1);
        cinema.addFilm(film2);
        cinema.addFilm(film3);
        cinema.addFilm(film4);
        cinema.addFilm(film5);
        cinema.addFilm(film6);
        cinema.addFilm(film7);
        cinema.addFilm(film8);
        cinema.addFilm(film9);
        cinema.addFilm(film10);
        cinema.addFilm(new Film(11, 2001, 9, HORROR, 2.3));

        for (Map.Entry<Integer, LinkedHashSet<Film>> entry : cinema.getMap().entrySet()) {
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }

        System.out.println("---------------------------------------------------");
        for (Film film : cinema.getFilmYear(2001)) {
            System.out.println(film);
        }

        System.out.println("---------------------------------------------------");
        for (Film film : cinema.getFilmYearAndMonth(2002, 7)) {
            System.out.println(film);
        }

        System.out.println("---------------------------------------------------");
        for (Film film : cinema.getFilmGenre(HORROR)) {
            System.out.println(film);
        }

        System.out.println("---------------------------------------------------");
        for (Film film : cinema.getTop10Film()) {
            System.out.println("Рейтинг " + film.getRating() + " - id: " + film.getId());
        }
    }
}
