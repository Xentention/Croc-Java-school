package ru.croc.task13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MoviesContainer {
    private final HashMap<Integer, String> movies;


    public MoviesContainer(String fileMoviesPath){
        movies = parseMovies(fileMoviesPath);
    }

    public HashMap<Integer, String> getMovies() {
        return movies;
    }

    /**
     * @param users Список пользователей
     * @return идентификатор фильма с наибольшим количеством
     *          просмотров
     */
    public Integer getIdOfRecommendation(ArrayList<User> users){
        HashMap<Integer, Integer> views = new HashMap<>();
        for (User user : users) {
            for(Integer movie : user.getViewingHistory()) {
                Integer count = views.get(movie);
                if (count == null)
                    count = 0;
                count++;
                views.put(movie, count);
            }
        }
        return getMovieWithMaxViews(views);
    }

    /**
     * @param views HashMap<Integer, Integer> идентификатор фильма и
     *              количество его просмотров
     * @return идентификатор фильма с макс просмотрами
     */
    private Integer getMovieWithMaxViews(HashMap<Integer, Integer> views){
        Integer movieWMaxViews = null;
        for (Integer movie : views.keySet()) {
            if (movieWMaxViews == null || views.get(movie) > views.get(movieWMaxViews)) {
                movieWMaxViews = movie;
            }
        }
        return movieWMaxViews;
    }

    /**
     * Парсит файл с фильмами
     * @return  HashMap<Integer, String> идентификаторов
     *          и названий фильмов
     */
    private static HashMap<Integer, String> parseMovies(String fileMoviesPath) {
        HashMap<Integer, String> movies = new HashMap<>();

        try (BufferedReader in = new BufferedReader(new FileReader(fileMoviesPath))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                Integer id = Integer.parseInt(currentLine.substring(0, currentLine.indexOf(',')));
                String name = currentLine.substring(currentLine.indexOf(',') + 1);
                movies.put(id, name);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return movies;
    }
}
