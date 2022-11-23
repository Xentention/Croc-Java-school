package ru.croc.task13;

import java.util.ArrayList;
import java.util.Scanner;

import static ru.croc.task13.User.getAllUsersFromHistory;

public class Task13 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<User> users = getAllUsersFromHistory();
        System.out.println("Введите историю просмотров без пробелов через запятую:");
        String newUserHistory = in.nextLine();
        User newUser = new User(newUserHistory);
        System.out.println(RecommendationSystem.recommendMovie(newUser, users));

        in.close();
    }
}
