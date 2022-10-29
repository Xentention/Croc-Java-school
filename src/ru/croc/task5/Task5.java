package ru.croc.task5;

import java.util.ArrayList;

public class Task5 {
    public static void main(String[] args) {
        ArrayList<Figure> figures= new ArrayList<>();
        figures.add(new Circle(10, 10, 5, "Moon"));
        figures.add(new Rectangle(10, 70, 50, 10, "Cloud"));
        figures.add(new Circle(1, 1, 1, "Birbie"));

        ArrayList<Annotation> annotations = new ArrayList<>();
        for (int i =0; i<figures.size(); ++i) {
            annotations.add(new Annotation(figures.get(i)));
            annotations.get(i).printAnnotation();
        }

    }
}
