package ru.croc.task6;

import static java.lang.Math.pow;

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

    public void move(double dX,
                     double dY){
        this.x += dX;
        this.y += dY;
    }

    public boolean checkIfContainsPoint(double x,
                                        double y) {
        return pow(x-this.x, 2) + pow(y-this.y, 2) <= pow(this.radius, 2);
    }

}
