package ru.croc.task8;

import java.io.*;

public class Task8 {
    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("Program expects exactly 1 argument");
        }

        int countWords = 0;
        //Открываем файл по пути, заданному первым аргументом ком. строки
        try (BufferedReader in = new BufferedReader(new FileReader(args[0]))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                String[] subLines = currentLine.split(" +");
                countWords += subLines.length;
            }
        } catch (IOException e) {
           throw new RuntimeException(e);
        }

        System.out.println(countWords);
    }
}
