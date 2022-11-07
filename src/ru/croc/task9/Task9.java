package ru.croc.task9;

import java.util.Stack;

public class Task9 {
    public static void main(String[] args){
        // строка, содержащая "входные" данные
        String filePath = "КРОК/работа/src/./../../универ/../../../мемы/котики";

        System.out.println(normalizeFilePath(filePath));

    }

    public static String normalizeFilePath(String path){
        // разбиваем путь на директории
        String[] dirs = path.split("/");

        Stack<String> curDir = new Stack<>();
        for (String dir:
             dirs) {
            switch (dir){
                case "..":
                    if (curDir.isEmpty() || curDir.peek().equals(".."))
                        curDir.push(dir);
                    else
                        curDir.pop();
                    break;
                case ".":
                    break;
                default:
                    curDir.push(dir);
            }
        }

        String[] updatedPath = new String[curDir.size()];
        return String.join("/", curDir.toArray(updatedPath));
    }

}
