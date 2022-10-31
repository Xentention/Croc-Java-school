package ru.croc.task6;

import java.util.ArrayList;

public class Task6 {
    public static void main(String[] args) {
        // Список тестовых данных
        ArrayList<Figure> figures= new ArrayList<>();
        figures.add(new Circle(10, 10, 5, "Moon"));
        figures.add(new Rectangle(10, 70, 50, 10, "Cloud"));
        figures.add(new Circle(1, 1, 1, "Birbie"));

        // Создаем список аннотаций
        System.out.println("Initial annotations:");
        Annotation[] annotations = new Annotation[figures.size()];
        for (int i =0; i<figures.size(); ++i) {
            annotations[i] = new Annotation(figures.get(i));
            System.out.println(annotations[i]);
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
            System.out.println(annotation);
        }

    }

}
