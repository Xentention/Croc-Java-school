package ru.croc.task13;

import java.util.ArrayList;

public class RecommendationSystem {
    private static final String fileMoviesPath = "src/ru/croc/task13/filesForTask13/allMovies.txt";
    private static MoviesContainer moviesContainer = new MoviesContainer(fileMoviesPath);

    /**
     *  Выбирает рекомендацию фильма на основе истории других пользователей
     * @param forWhom User, для которого подбираем фильм
     * @param otherUsers ArrayList<User> список других пользователей
     * @return возвращает название фильма
     */
    public static String recommendMovie(User forWhom,
                                        ArrayList<User> otherUsers) {
        otherUsers = findWithSimilarHistory(forWhom, otherUsers);
        for (User user:
             otherUsers) {
            System.out.println(user.getViewingHistory());
        }
        String recommendation = moviesContainer.getMovies()
                                .get(moviesContainer.getIdOfRecommendation(otherUsers));
        return recommendation == null ?
                "Список рекомендаций пока пуст." :
                recommendation;

    }

    /**
     * Находит список юзеров, история уникальных просмотров которых
     * содержит хотя бы половину просмотренных фильмов
     *
     * @param forWhom пользователь, с которым ищем совпадения
     * @param otherUsers все пользователи
     * @return ArrayList<User> список пользователей, с которыми история совпадает
     *          минимум наполовину
     */
    private static ArrayList<User> findWithSimilarHistory(User forWhom,
                                                          ArrayList<User> otherUsers) {

        ArrayList<User> usersWithSimilarHistory = new ArrayList<>();
        for (User user : otherUsers) {

            ArrayList<Integer> historyOverlap = new ArrayList<>(forWhom.getNoDuplicatesHistory());
            historyOverlap.retainAll(user.getNoDuplicatesHistory());

            if(!historyOverlap.isEmpty()
                && 2 * historyOverlap.size() >= forWhom.getNoDuplicatesHistory().size()) {
                user.getViewingHistory().removeAll(historyOverlap);
                usersWithSimilarHistory.add(new User(user.getViewingHistory()));
            }
        }
        return usersWithSimilarHistory;
    }

}
