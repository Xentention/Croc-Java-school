package ru.croc.task6;

import java.util.ArrayList;

public class Task6 {
    public static void main(String[] args) {
        // Список входных данных
        ArrayList<Figure> figures= new ArrayList<>();
        figures.add(new Circle(10, 10, 5, "Moon"));
        figures.add(new Rectangle(10, 70, 50, 10, "Cloud"));
        figures.add(new Circle(1, 1, 1, "Birbie"));

        // Создаем список аннотаций
        System.out.println("Initial annotations:");
        Annotation[] annotations = new Annotation[figures.size()];
        for (int i =0; i<figures.size(); ++i) {
            annotations[i] = new Annotation(figures.get(i));
            annotations[i].printAnnotation();
        }

        // перемещаем некоторые объекты
        try {
            annotations[findIndexByLabel("Moo", annotations)].move(3, 3);
            System.out.println("Annotation that contains 'Moo' was moved by (3, 3)");
        } catch (Exception ObjectNotFound){
            System.out.println("For the label 'Moo' " + ObjectNotFound.getMessage());
        }

        try {
            annotations[findIndexByPoint(0, 0, annotations)].move(-3, 10);
            System.out.println("Annotation that contains (0, 0) was moved by (-3, 10)");
        } catch (Exception ObjectNotFound){
            System.out.println(ObjectNotFound.getMessage() + " that contains a (0, 0) point.");
        }

        try {
            annotations[findIndexByPoint(33, 47, annotations)].move(13, 0);
            System.out.println("Annotation that contains (33, 47) was moved by (13, 0)");
        } catch (Exception ObjectNotFound){
            System.out.println(ObjectNotFound.getMessage() + " that contains a (14, 17) point.");
        }

        // выводим обновленный массив
        System.out.println("Updated annotations:");
        for (Annotation annotation
            : annotations) {
            annotation.printAnnotation();
        }

    }

    public static int findIndexByLabel(String label,
                                       Annotation... annotations) throws Exception {
        for (int i = 0; i < annotations.length; ++i) {
            if(annotations[i].checkIfContainsLabel(label)) return i;
        }
        throw new Exception("Nothing was found");
    }

    public static int findIndexByPoint(double x,
                                       double y,
                                       Annotation... annotations) throws Exception {
        for (int i = 0; i < annotations.length; ++i)  {
            if(annotations[i].checkIfContainsPoint(x, y)) return i;
        }
        throw new Exception("Nothing was found");
    }
}
