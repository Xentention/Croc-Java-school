package ru.croc.task16;

import java.util.Comparator;

/**
 * Compares Logs by time
 */
public class LogComparator implements Comparator<LogReader> {

    @Override
    public int compare(LogReader o1, LogReader o2) {
        return Long.compare(o1.getLastRead().time(), o2.getLastRead().time());
    }
}
