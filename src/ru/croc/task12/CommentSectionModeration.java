package ru.croc.task12;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CommentSectionModeration implements BlackListFilter{
    @Override
    public void filterComments(List<String> comments,
                               Set<String> blackList) {
        String comment;
        // итератор по элементам comments
        Iterator<String>itComments = comments.iterator();
        // пока не прошли весь список
        while(itComments.hasNext()){
            comment = itComments.next();    // автоматически переходит к следующему в comments!!
            // итератор по элементам blackList
            Iterator<String> itBlackList = blackList.iterator();
            while (itBlackList.hasNext()) {
                if(comment.contains(itBlackList.next()))    // автоматически переходит к следующему!!
                    itComments.remove();
            }
        }
    }
}
