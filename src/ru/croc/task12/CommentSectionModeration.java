package ru.croc.task12;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CommentSectionModeration implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments,
                               Set<String> blackList) {

        // итератор по элементам comments
        Iterator<String>itComments = comments.iterator();
        // пока не прошли весь список
        while(itComments.hasNext()){
            String comment = itComments.next();    // автоматически переходит к следующему в comments!!
            for (String bannedStr : blackList) {
                if (comment.contains(bannedStr))
                    itComments.remove();
            }
        }
    }
}
