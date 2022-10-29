package ru.croc.task6;

public class Circle extends Figure {
    protected double x;
    protected double y;
    protected double radius;

    public Circle(double x,
                  double y,
                  double radius,
                  String label){
        type = FigureTypes.CIRCLE;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.label = label;

    }

    public void move(double dX,
                     double dY){
        this.x += dX;
        this.y += dY;
    }

}
