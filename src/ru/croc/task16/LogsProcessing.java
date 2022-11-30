package ru.croc.task16;

import java.io.File;
import java.util.*;

import static ru.croc.task16.Log.parseFile;

public class LogsProcessing {
    private final File directory;
    private final List<File> allFiles = new ArrayList<>();
    private final ArrayList<ArrayList<Log>> logsFromFiles = new ArrayList<>();

    public LogsProcessing(String directoryPath) throws CannotParseLogsExc {
        this.directory = new File(directoryPath);
        getAllFilesPaths();
        getFilesLogs();
    }

    /**
     * Prints all logs from files in a standard output in a chronological order
     */
    public void mergeLogs() {
        //copying an ArrayList, so we don't have to change a class' attribute
        ArrayList<ArrayList<Log>> logsFromFiles = new ArrayList<>(this.logsFromFiles);
        while (!logsFromFiles.isEmpty()) {
            int minTimeInd = 0;
            for (int i = 0; i < logsFromFiles.size(); ++i) {
                if(logsFromFiles.get(i).isEmpty()) {
                    logsFromFiles.remove(i);
                    --i;
                }
                else if(logsFromFiles.get(i).get(0).getTime() <
                        logsFromFiles.get(minTimeInd).get(0).getTime())
                    minTimeInd = i;
            }
            if(logsFromFiles.isEmpty())
                break;
            System.out.println(logsFromFiles.get(minTimeInd).get(0).toString());
            logsFromFiles.get(minTimeInd).remove(0);
        }

    }

    private void getFilesLogs() throws CannotParseLogsExc {
        for (File file : allFiles)
            logsFromFiles.add(parseFile(file));
    }

    private void getAllFilesPaths() {
        for (File file:
                Objects.requireNonNull(directory.listFiles())) {
            if(!file.getName().endsWith(".log")
                    && !file.getName().endsWith(".trace"))
                continue;
            allFiles.add(file);

        }

    }

}
