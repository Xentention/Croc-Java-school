package ru.croc.task2;

import java.util.HashMap;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args){
        System.out.println("Введите число байтов:");
        Scanner in = new Scanner(System.in);

        double bytesamount = in.nextDouble();
        //Заполняем доступными единицами измерения
        HashMap<Integer, String> QuantityPrefixes = new HashMap<>();
        QuantityPrefixes.put(0, "B");
        QuantityPrefixes.put(1, "KB");
        QuantityPrefixes.put(2, "MB");
        QuantityPrefixes.put(3, "GB");
        QuantityPrefixes.put(4, "TB");
        QuantityPrefixes.put(5, "PB");
        int i = 0;
        while((i < 5) && (bytesamount >=1024)) {
            bytesamount /= 1024;
            i++;
        }

        System.out.printf("%.1f", bytesamount);
        System.out.println(QuantityPrefixes.get(i));
        in.close();
    }
}
