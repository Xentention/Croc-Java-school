package ru.croc.task16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Log {
    private final Integer timestamp;
    private final String info;

    public Log(Integer timestamp,
               String info){
        this.timestamp = timestamp;
        this.info = info;
    }


    public Integer getTimestamp() {
        return timestamp;
    }

    public String getInfo() {
        return info;
    }

    /**
     * Returns logs in a file
     * @param file  path
     * @return ArrayList<Log> of logs in a file
     * @throws CannotParseLogs signals that a file cannot be parsed
     *                          correctly for some reason
     */
    public static ArrayList<Log> parseFile(File file) throws CannotParseLogs {
        ArrayList<Log> logs = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                String[] split = currentLine.split(" ");
                logs.add(new Log(Integer.parseInt(split[0]), split[1]));
            }
        } catch (IOException e) {
            throw new CannotParseLogs(e);
        }
        return logs;
    }
}
