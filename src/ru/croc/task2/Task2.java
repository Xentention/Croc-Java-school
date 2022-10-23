package ru.croc.task2;

import java.util.HashMap;
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args){
        System.out.println("Введите число байтов:");
        Scanner in = new Scanner(System.in);

        double bytesamount = in.nextDouble();
        //Заполняем доступными единицами измерения
        HashMap<Integer, String> quantityPrefixes = new HashMap<>();
        quantityPrefixes.put(0, "B");
        quantityPrefixes.put(1, "KB");
        quantityPrefixes.put(2, "MB");
        quantityPrefixes.put(3, "GB");
        quantityPrefixes.put(4, "TB");
        quantityPrefixes.put(5, "PB");
        int i = 0;
        while((i < 5) && (bytesamount >=1024)) {
            bytesamount /= 1024;
            i++;
        }

        System.out.printf("%.1f", bytesamount);
        System.out.println(quantityPrefixes.get(i));
        in.close();
    }
}
