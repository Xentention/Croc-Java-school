package ru.croc.task16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class LogsProcessing {
    private final File rootDirectory;
    private final List<File> allFiles = new ArrayList<>();
    private final List<LogReader> allReaders = new ArrayList<>();

    public LogsProcessing(String directoryPath) throws FileNotFoundException {
        this.rootDirectory = new File(directoryPath);
        if(!rootDirectory.exists())
            throw new FileNotFoundException();

        getAllFilesPaths(rootDirectory);
        for (File file : allFiles) {
            allReaders.add(new LogReader(file));
        }
    }

    /**
     * Prints all logs from files in a standard output in a chronological order
     */
    public void mergeLogs() throws CannotParseLogsExc {
        //copying an ArrayList, so we don't have to change a class' attribute
        ArrayList<LogReader> allReadersCopy = new ArrayList<>(this.allReaders);
        // creating a PriorityQueue to automatically compare logs
        Queue<Log> oldestLogs = new PriorityQueue<>(new LogComparator());

        while (!allReadersCopy.isEmpty() || !oldestLogs.isEmpty()) {
            for (int i =0 ; i < allReadersCopy.size(); ++i) {
                Log newLog;
                try {
                    newLog = allReadersCopy.get(i).readNext();
                    oldestLogs.add(newLog);
                } catch (LogReader.FileEndedExc e) {
                    // удаляем считыватель из пустого файла
                    allReadersCopy.remove(i);
                    --i;
                }
            }
            if(!oldestLogs.isEmpty())
                System.out.println(oldestLogs.poll());
        }

    }


    private void getAllFilesPaths(File directory) {
        for (File file: Objects.requireNonNull(directory.listFiles())) {
            if(file.isDirectory())
                getAllFilesPaths(file);
            if(!file.getName().toLowerCase().endsWith(".log")
                    && !file.getName().toLowerCase().endsWith(".trace"))
                continue;
            allFiles.add(file);

        }

    }

}
