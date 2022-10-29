package ru.croc.task6;

import java.util.ArrayList;
import static java.lang.Math.pow;

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

        try {
            annotations[findIndexByLabel("Moo", annotations)].move(3, 3);
        } catch (Exception ObjectNotFound){
            System.out.println("For the label 'Moo' " + ObjectNotFound.getMessage());
        }

        try {
            annotations[findIndexByPoint(0, 0, annotations)].move(-3, 10);
        } catch (Exception ObjectNotFound){
            System.out.println(ObjectNotFound.getMessage() + " that contains a (10, 10) point.");
        }

        try {
            annotations[findIndexByPoint(33, 47, annotations)].move(13, 0);
        } catch (Exception ObjectNotFound){
            System.out.println(ObjectNotFound.getMessage() + " that contains a (14, 17) point.");
        }


        System.out.println("Updated annotations:");
        for (Annotation annotation
            : annotations) {
            annotation.printAnnotation();
        }

    }

    public static int findIndexByLabel(String label,
                                       Annotation... annotations) throws Exception {
        for (int i = 0; i < annotations.length; ++i) {
            if(annotations[i].figure.label.contains(label)) return i;
        }
        throw new Exception("Nothing was found");
    }

    public static int findIndexByPoint(double x,
                                       double y,
                                       Annotation... annotations) throws Exception {
        for (int i = 0; i < annotations.length; ++i)  {
            boolean havefound = switch (annotations[i].figure.type) {
                case CIRCLE -> checkIfCircleContainsPoint(x, y, (Circle) annotations[i].figure);
                case RECTANGLE -> checkIfRectContainsPoint(x, y, (Rectangle) annotations[i].figure);
            };
            if(havefound) return i;
        }
        throw new Exception("Nothing was found");
    }

    private static boolean checkIfCircleContainsPoint(double x,
                                                      double y,
                                                      Circle circle){
        return pow(x-circle.x, 2) + pow(y-circle.y, 2) <= pow(circle.radius, 2);
    }

    private static boolean checkIfRectContainsPoint(double x,
                                                    double y,
                                                    Rectangle rect){
        // используется экранная система координат
        return (rect.LeftBottom[0] <= x && rect.RightTop[0] >= x) &&
                (rect.RightTop[1] <= y && rect.LeftBottom[1] >= y);
    }
}
