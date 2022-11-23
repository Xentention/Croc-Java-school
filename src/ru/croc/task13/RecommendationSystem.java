package ru.croc.task13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RecommendationSystem {
    private static final String fileMoviesPath = "filePath\\allMovies.txt";
    private static final HashMap<Integer, String> movies = parseMovies();

    /**
     *  Выбирает рекомендацию фильма на основе истории других пользователей
     * @param forWhom User, для которого подбираем фильм
     * @param otherUsers ArrayList<User> список других пользователей
     * @return возвращает название фильма
     */
    public static String recommendMovie(User forWhom,
                                        ArrayList<User> otherUsers) {
        otherUsers = findWithSimilarHistory(forWhom, otherUsers);
        String recommendation = movies.get(getIdOfRecommendation(otherUsers));
        return recommendation == null ?
                "Список рекомендаций пока пуст." :
                recommendation;

    }

    /**
     *
     * @param forWhom пользователь, с которым ищем совпадения
     * @param otherUsers все пользователи
     * @return ArrayList<User> список пользователей, с которыми история совпадает
     *          минимум наполовину
     */
    private static ArrayList<User> findWithSimilarHistory(User forWhom,
                                                          ArrayList<User> otherUsers) {
        ArrayList<User> usersWithSimilarHistory = new ArrayList<>();
        for (User user:
                otherUsers) {
            ArrayList<Integer> thisXORHistory = new ArrayList<>(user.getViewingHistory());
            thisXORHistory.retainAll(forWhom.getViewingHistory());

            if(thisXORHistory.size() >= user.getViewingHistory().size() / 2) {
                user.getViewingHistory().removeAll(thisXORHistory);
                usersWithSimilarHistory.add(user);
            }
        }
        return usersWithSimilarHistory;
    }

    /**
     * @param users Список пользователей
     * @return идентификатор фильма с наибольшим количеством
     *          просмотров
     */
    private static Integer getIdOfRecommendation(ArrayList<User> users){
        HashMap<Integer, Integer> views = new HashMap<>();
        for (User user:
                users) {
            for(Integer movie:
                    user.getViewingHistory()) {
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
    private static Integer getMovieWithMaxViews(HashMap<Integer, Integer> views){
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
    private static HashMap<Integer, String> parseMovies() {
        HashMap<Integer, String> movies = new HashMap<>();

        try (BufferedReader in = new BufferedReader(new FileReader(fileMoviesPath))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {;
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
