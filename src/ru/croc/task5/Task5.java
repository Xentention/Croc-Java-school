package ru.croc.task5;

import java.util.ArrayList;

public class Task5 {
    public static void main(String[] args) {
        // список тестовых данных
        ArrayList<Figure> figures = new ArrayList<>();
        figures.add(new Circle(10, 10, 5, "Moon"));
        figures.add(new Rectangle(10, 70, 50, 10, "Cloud"));
        figures.add(new Circle(1, 1, 1, "Birbie"));

        // Создаем список аннотаций
        Annotation[] annotations = new Annotation[figures.size()];
        for (int i =0; i < figures.size(); ++i) {
            annotations[i] = new Annotation(figures.get(i));
            System.out.println(annotations[i]);
        }

    }
}
