package ru.croc.task1;

import java.util.Scanner;

public class Task1 {

    //��������������� ��������� Point
    static class Point {
    double x;
    double y;

    //��������� ���������� � �������
    public void getNewPoint(Scanner in){
        System.out.println("������� ���������� x:");
        x = in.nextDouble();
        System.out.println("������� ���������� y:");
        y = in.nextDouble();
    }
  }

    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);

        System.out.println("������� A:");
        Point A = new Point();
        A.getNewPoint(in);
        System.out.println("������� B:");
        Point B = new Point();
        B.getNewPoint(in);
        System.out.println("������� C:");
        Point C = new Point();
        C.getNewPoint(in);

        //������� ������� �� ���������� �����������
        System.out.println("������� ������������:");
        System.out.println(Math.abs((B.x - A.x) * (C.y - A.y) - (C.x - A.x) * (B.y - A.y)) / 2);

        in.close();
    }
}
