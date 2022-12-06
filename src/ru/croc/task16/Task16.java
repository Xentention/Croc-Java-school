package ru.croc.task16;

import java.io.FileNotFoundException;

public class Task16 {
    public static void main(String[] args) {
        try {
            LogsProcessing lp = new LogsProcessing("src/ru/croc/task16/logFiles");
            lp.mergeLogs();
        } catch (final FileNotFoundException e){
            System.out.println("Directory doesn't exist");

        } catch (CannotParseLogsExc e) {
            System.out.println("Something went wrong in " + e.getProblemFile());
        }
    }
}
