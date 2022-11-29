package ru.croc.task16;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LogsProcessing {
    private final File directory;
    private final List<File> allFiles = new ArrayList<>();

    LogsProcessing(String directoryPath){
        this.directory = new File(directoryPath);
        getAllFilesPaths();
    }

    public void mergeLogs() throws CannotParseLogs {
        TreeMap<Integer, String> currentLines = new TreeMap<>();
        while(!allFiles.isEmpty()) {
            for (int i = 0; i < allFiles.size(); ++i) {
                Pair<Integer, String> log = parseLine(allFiles.get(i));
                if(log == null) {
                    allFiles.remove(i);
                    --i;
                }
                else
                    currentLines.put(log.getKey(), log.getValue());
            }
            System.out.println(currentLines.firstKey() + " "
                    + currentLines.get(currentLines.firstKey()));
            currentLines.remove(currentLines.firstKey());
        }

    }

    private Pair<Integer, String> parseLine(File path) throws CannotParseLogs {
        String[] splitLine;
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String currentLine = in.readLine();
            if(currentLine == null)
                return null;
            splitLine = currentLine.split(" ");
        } catch (IOException e) {
            throw new CannotParseLogs(e);
        }

        return new Pair<>(Integer.parseInt(splitLine[0]), splitLine[1]);
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
