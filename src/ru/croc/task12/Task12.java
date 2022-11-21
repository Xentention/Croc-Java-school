package ru.croc.task12;

import java.util.*;

public class Task12 {
    public static void main(String[] args) {
        String[] commentsArray = {
                "The video is a piece of shit",
                "\"been trapped in this basement for five years\" killed me lmfao",
                "Every point you made is perfect",
                "This is a great video",
                "Check out my new video https://youtube.com/***",
                "Thank you so much for a wonderful video",
                "Go die"
        };
        String[] blackListArray = {
                "shit", "die", "Check out my"
        };
        BlackListFilter filter = new CommentSectionModeration();

        System.out.println("Для LinkedList и HashSet");
        List<String> commentsLL = new LinkedList<>(Arrays.asList(commentsArray));
        Set<String> blackListHS = new HashSet<>(Arrays.asList(blackListArray));
        filter.filterComments(commentsLL, blackListHS);
        for (String comment : commentsLL) {
            System.out.println(comment);
        }

        System.out.println();

        System.out.println("Для ArrayList и TreeSet");
        List<String> commentsAL = new ArrayList<>(Arrays.asList(commentsArray));
        Set<String> blackListTS = new TreeSet<>(Arrays.asList(blackListArray));
        filter.filterComments(commentsAL, blackListTS);
        for (String comment : commentsAL) {
            System.out.println(comment);
        }
    }

}
