package ru.croc.task16;

import java.io.File;

public class CannotParseLogsExc extends Exception {
    private final File problemFile;

    CannotParseLogsExc(Throwable cause,
                       File problemFile) {
        super(cause);
        this.problemFile = problemFile;
    }

    CannotParseLogsExc(File problemFile) {
        this.problemFile = problemFile;
    }

    public File getProblemFile() {
        return problemFile;
    }
}
