package ru.croc.task16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class LogsProcessing {
    private final File rootDirectory;
    private final List<File> allFiles = new ArrayList<>();

    public LogsProcessing(String directoryPath) throws FileNotFoundException {
        this.rootDirectory = new File(directoryPath);
        if(!rootDirectory.exists())
            throw new FileNotFoundException();
        getAllFilesPaths(rootDirectory);
    }

    /**
     * Prints all logs from files in a standard output in a chronological order
     */
    public void mergeLogs() throws CannotParseLogsExc {
        // creating a PriorityQueue to automatically compare logs
        Queue<LogReader> oldestLogs = new PriorityQueue<>(new LogComparator());
        // adding LogReaders for all files
        for (File file: allFiles) {
            try {
                oldestLogs.add(new LogReader(file));
            } catch (FileNotFoundException e) {
                throw new CannotParseLogsExc(e, file);
            } catch (LogReader.FileEndedExc e) {
                // don't add empty files' readers
            }
        }

        while (!oldestLogs.isEmpty()) {
            LogReader newLR = oldestLogs.poll();
            System.out.println(newLR.getLastRead());
            try {
                newLR.readNext();
                oldestLogs.add(newLR);
            } catch (LogReader.FileEndedExc e) {
                // deleted LogReader of an ended file
            }
        }

    }


    private void getAllFilesPaths(File directory) {
        for (File file: Objects.requireNonNull(directory.listFiles())) {
            if(file.isDirectory()) {
                getAllFilesPaths(file);
                continue;
            }
            if(!file.getName().toLowerCase().endsWith(".log")
                    && !file.getName().toLowerCase().endsWith(".trace"))
                continue;
            allFiles.add(file);

        }

    }

}
