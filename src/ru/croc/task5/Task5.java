package ru.croc.task5;

import java.util.ArrayList;

public class Task5 {
    public static void main(String[] args) {
        // список тестовых данных
        ArrayList<Figure> figures = new ArrayList<>();
        figures.add(new Circle(10, 10, 5));
        figures.add(new Rectangle(10, 70, 50, 10));
        figures.add(new Circle(1, 1, 1));

        // Создаем список аннотаций
        Annotation[] annotations = new Annotation[figures.size()];
        annotations[0] = new Annotation(figures.get(0), "Moon");
        annotations[1] = new Annotation(figures.get(1), "Cloud");
        annotations[2] = new Annotation(figures.get(2), "Birbie");

        for (Annotation annotation
                : annotations) {
            System.out.println(annotation.toString());
        }

    }
}
