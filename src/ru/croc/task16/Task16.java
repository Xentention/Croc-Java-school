package ru.croc.task16;

public class Task16 {
    public static void main(String[] args) {
        LogsProcessing lp = new LogsProcessing("src/ru/croc/task16/logFiles");
        try {
            lp.mergeLogs();
        } catch (CannotParseLogs e) {
            System.out.println("Something went wrong");
        }
    }
}
