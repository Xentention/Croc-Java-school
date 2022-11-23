package ru.croc.task13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class User {
    private static final String fileHistoryPath = "filePath";
    private ArrayList<Integer> viewingHistory = new ArrayList<>();

    public User(ArrayList<Integer> viewingHistory) {
        this.viewingHistory = viewingHistory;

    }

    public User(String viewingHistory) {
        String[] history = viewingHistory.split(",");
        for (String id:
                history) {
            this.viewingHistory.add(Integer.parseInt(id));
        }

    }

    /**
     * @return Возвращает список пользователей из файла с историями
     *          просмотров
     */
    public static ArrayList<User> getAllUsersFromHistory(){
        ArrayList<ArrayList<Integer>> usersHistory = parseHistory();
        ArrayList<User> users = new ArrayList<>();
        for (ArrayList<Integer> userHistory:
                usersHistory) {
            users.add(new User(userHistory));
        }
        return users;
    }

    public ArrayList<Integer> getViewingHistory() {
        return viewingHistory;
    }

    /**
     * Парсит файл с историей пользователей
     * @return ArrayList<ArrayList<Integer>> - список списков
     *          истории просмотров пользователей
     */
    private static ArrayList<ArrayList<Integer>> parseHistory(){
        ArrayList<ArrayList<Integer>> usersHistory = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileHistoryPath))) {
            String currentLine;
            int currentUserNum = 0;
            while ((currentLine = in.readLine()) != null) {
                usersHistory.add(new ArrayList<>());
                String[] history = currentLine.split(",");
                for (String id:
                     history) {
                    usersHistory.get(currentUserNum).add(Integer.parseInt(id));
                }
                currentUserNum++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return usersHistory;
    }

}
