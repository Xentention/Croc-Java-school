package ru.croc.task14;

import java.util.*;
import java.util.function.Predicate;


public interface BlackListFilter {
    /**
     * From the given Iterable of comments removes ones
     *that contain words from the black list.
     *
     * @param comments Iterable of comments; every comment
     *                 is a class object
     *
     * @param filter   Filtration method that checks, if
     *                 an element should be filtered out
     *
     * @return         A collection of moderated comments
     */
    default <T> Collection<T> filterComments(Iterable<T> comments,
                                             Predicate<T> filter){
            Collection<T> filteredComments = new LinkedList<>();
            for(T comment:
                comments){
                if(!filter.test(comment))
                    filteredComments.add(comment);
            }
            return filteredComments;
    }
}
