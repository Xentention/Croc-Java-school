package ru.croc.task5;


public class Annotation {
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
