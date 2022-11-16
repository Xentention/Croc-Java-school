package ru.croc.task10;


import static ru.croc.task10.Solution.calculatePassword;

public class Task10 {
    public static void main(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("Program expects exactly 2 arguments");
        }

        System.out.println(calculatePassword(Integer.parseInt(args[0]), args[1]));
    }
}
