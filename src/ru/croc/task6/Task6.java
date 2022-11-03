package ru.croc.task6;

import java.util.ArrayList;

public class Task6 {
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

        for (Annotation annotation : annotations) {
            System.out.println(annotation.toString());
        }

        // создаем класс разметки картинки
        AnnotatedImage testAnnImg = new AnnotatedImage("some-path", annotations);

        // перемещаем некоторые объекты
        try {
            testAnnImg.findByLabel("Moo").move(3, 3);
            System.out.println("Annotation that contains 'Moo' was moved by (3, 3)");
        } catch (ObjectNotFoundExc ObjectNotFound){
            System.out.println("For the label 'Moo' " + ObjectNotFound.getMessage());
        }

        try {
            testAnnImg.findByPoint(0, 0).move(-3, 10);
            System.out.println("Annotation that contains (0, 0) was moved by (-3, 10)");
        } catch (ObjectNotFoundExc ObjectNotFound){
            System.out.println(ObjectNotFound.getMessage() + " that contains a (0, 0) point.");
        }

        try {
            testAnnImg.findByPoint(33, 47).move(13, 0);
            System.out.println("Annotation that contains (33, 47) was moved by (13, 0)");
        } catch (ObjectNotFoundExc ObjectNotFound){
            System.out.println(ObjectNotFound.getMessage() + " that contains a (14, 17) point.");
        }

        // обновляем наш тестовый массив
        annotations = testAnnImg.getAnnotations();
        // выводим обновленный массив
        System.out.println("Updated annotations:");
        for (Annotation annotation
            : annotations) {
            System.out.println(annotation.toString());
        }

    }

}
