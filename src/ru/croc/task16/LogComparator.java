package ru.croc.task16;

import java.util.Comparator;

/**
 * Compares Logs by time
 */
public class LogComparator implements Comparator<Log> {

    @Override
    public int compare(Log o1, Log o2) {
        return Long.compare(o1.time(), o2.time());
    }
}
