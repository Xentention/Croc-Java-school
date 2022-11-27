package ru.croc.task14.tests;

import org.testng.annotations.Test;
import ru.croc.task14.BlackListFilter;

import java.util.*;
import java.util.function.Predicate;

import static org.testng.AssertJUnit.assertEquals;

public class BlackListFilterTest {
    private final String[] commentsArray = {
            "The video is a piece of shit",
            "\"been trapped in this basement for five years\" killed me lmfao",
            "Every point you made is perfect",
            "This is a great video",
            "Check out my new video https://youtube.com/***",
            "Thank you so much for a wonderful video",
            "Go die"
    };
    private final String[] blackListArray = {
            "shit", "die", "Check out my"
    };
    private final String[] filteredComments = {
            "\"been trapped in this basement for five years\" killed me lmfao",
            "Every point you made is perfect",
            "This is a great video",
            "Thank you so much for a wonderful video"
    };

    @Test
    private void listTest(){
        List<String> comments = Arrays.asList(this.commentsArray);
        List<String> blackList = Arrays.asList(this.blackListArray);
        List<String> filteredComments = Arrays.asList(this.filteredComments);

        BlackListFilter moderation = new BlackListFilter() { };
        Predicate<String> filter = s -> blackList.stream().anyMatch(s::contains);

        assertEquals(filteredComments, moderation.filterComments(comments, filter));
    }

    @Test
    private void setTest(){
        Set<String> comments = Set.of(this.commentsArray);
        Set<String> blackList = Set.of(this.blackListArray);
        Set<String> filteredComments = Set.of(this.filteredComments);

        BlackListFilter moderation = new BlackListFilter() { };
        Predicate<String> filter = s -> blackList.stream().anyMatch(s::contains);

        assertEquals(filteredComments, new HashSet<>(moderation.filterComments(comments, filter)));
    }

}
