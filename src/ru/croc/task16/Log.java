package ru.croc.task16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Log {
    private final Integer time;
    private final String message;

    public Log(Integer time,
               String message){
        this.time = time;
        this.message = message;
    }

    public String toString(){
        return time + " " + message;
    }

    public Integer getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Returns logs in a file
     * @param file  path
     * @return ArrayList<Log> of logs in a file
     * @throws CannotParseLogsExc signals that a file cannot be parsed
     *                          correctly for some reason
     */
    public static ArrayList<Log> parseFile(File file) throws CannotParseLogsExc {
        ArrayList<Log> logs = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                String[] split = currentLine.split(" ");
                logs.add(new Log(Integer.parseInt(split[0]), split[1]));
            }
        } catch (IOException | NumberFormatException e) {
            throw new CannotParseLogsExc(e, file);
        }
        return logs;
    }
}
