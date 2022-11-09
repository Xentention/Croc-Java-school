package ru.croc.task8;

import java.io.*;

public class Task8 {
    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("Program expects exactly 1 argument");
        }

        System.out.println(countWordsInFile(args[0]));
    }

    public static int countWordsInFile(String path){
        int countWords = 0;
        //Открываем файл по заданному пути
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String currentLine;
            while ((currentLine = in.readLine()) != null) {           // пока в файле есть еще строчки
                if(currentLine.equals("")) continue;                  // пропускаем пустые строки
                String[] subLines = currentLine.split(" +");    // делим строку на слова
                countWords += subLines.length;                        // считаем количество слов
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return countWords;
    }
}
