package ru.croc.task1;

import java.util.Scanner;

public class Task1 {

    //вспомогательная структура Point
    static class Point {
    double x;
    double y;

    //Считываем координаты с консоли
    public void getNewPoint(Scanner in){
        System.out.println("Введите координату x:");
        x = in.nextDouble();
        System.out.println("Введите координату y:");
        y = in.nextDouble();
    }
  }

    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);

        System.out.println("Вершина A:");
        Point A = new Point();
        A.getNewPoint(in);
        System.out.println("Вершина B:");
        Point B = new Point();
        B.getNewPoint(in);
        System.out.println("Вершина C:");
        Point C = new Point();
        C.getNewPoint(in);

        //Находим площадь по декартовым координатам
        System.out.println("Площадь треугольника:");
        System.out.println(Math.abs((B.x - A.x) * (C.y - A.y) - (C.x - A.x) * (B.y - A.y)) / 2);

        in.close();
    }
}
