package ru.croc.task5;

public class Annotation {
    private Figure figure;
    private String annotatedFigure;

    public Annotation(Figure newFigure){
        this.figure = newFigure;
        switch (this.figure.type) {
            case CIRCLE -> toString((Circle) figure);
            case RECTANGLE -> toString((Rectangle) figure);
            default -> annotatedFigure = "Unknown figure type. Could not write an annotation";
        }

    }

    public void printAnnotation(){
        System.out.println(annotatedFigure);
    }

    private void toString(Circle circle){
        this.annotatedFigure = "C (" + circle.x + ", " + circle.y + "), "
                                + circle.radius + ": " + circle.label;
    }

    private void toString(Rectangle rectangle){
        this.annotatedFigure = "R (" + rectangle.LeftBottom[0] + ", " + rectangle.LeftBottom[1] + "), "
                                + "(" + rectangle.RightTop[0] + ", " + rectangle.RightTop[1] + "): "
                                + rectangle.label;
    }

}
