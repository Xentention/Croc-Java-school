package ru.croc.task5;

public class Circle extends Figure {
    protected double x;
    protected double y;
    protected double radius;

    public Circle(double x,
                  double y,
                  double radius){
        this.x = x;
        this.y = y;
        this.radius = radius;

    }
    public String toString(){
        return "C (" + this.x + ", " + this.y
                + "), " + this.radius;
    }

}
