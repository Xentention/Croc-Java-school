package ru.croc.task6;

import static java.lang.Math.pow;

public class Annotation implements Movable {
    protected Figure figure;

    public Annotation(Figure newFigure){
        this.figure = newFigure;
    }

    public String toString(){
        return switch (this.figure.type) {
            case CIRCLE -> circleToString((Circle) figure);
            case RECTANGLE -> rectangleToString((Rectangle) figure);
        };
    }

    public void move(double dX,
                     double dY){
        switch (this.figure.type) {
            case CIRCLE -> moveCircle(dX, dY, (Circle) figure);
            case RECTANGLE -> moveRectangle(dX, dY, (Rectangle) figure);
        }
    }

    private void moveCircle(double dX,
                            double dY,
                            Circle circle){
        circle.move(dX, dY);
    }

    private void moveRectangle(double dX,
                               double dY,
                               Rectangle rectangle){
        rectangle.move(dX, dY);
    }


    public boolean checkIfContainsLabel(String label){
        return this.figure.label.contains(label);
    }

    public boolean checkIfContainsPoint(double x,
                                        double y){
        return switch (this.figure.type) {
            case CIRCLE -> checkIfCircleContainsPoint(x, y, (Circle) this.figure);
            case RECTANGLE -> checkIfRectContainsPoint(x, y, (Rectangle) this.figure);
        };
    }

    private boolean checkIfCircleContainsPoint(double x,
                                               double y,
                                               Circle circle){
        return pow(x-circle.x, 2) + pow(y-circle.y, 2) <= pow(circle.radius, 2);
    }

    private boolean checkIfRectContainsPoint(double x,
                                             double y,
                                             Rectangle rect){
        // используется экранная система координат
        return (rect.LeftBottom[0] <= x && rect.RightTop[0] >= x) &&
                (rect.RightTop[1] <= y && rect.LeftBottom[1] >= y);
    }

    private String circleToString(Circle circle){
        return "C (" + circle.x + ", " + circle.y + "), "
                                + circle.radius + ": " + circle.label;
    }

    private String rectangleToString(Rectangle rectangle){
        return "R (" + rectangle.LeftBottom[0] + ", " + rectangle.LeftBottom[1] + "), "
                                + "(" + rectangle.RightTop[0] + ", " + rectangle.RightTop[1] + "): "
                                + rectangle.label;
    }

}
