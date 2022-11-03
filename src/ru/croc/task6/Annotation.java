package ru.croc.task6;

import static java.lang.Math.pow;

public class Annotation implements Movable {
    protected Figure figure;
    protected String label;

    public Annotation(Figure figure, String label){
        this.figure = figure;
        this.label = label;
    }

    public String toString(){
        return this.figure.toString() + ": " + label;
    }

    public void move(double dX,
                     double dY){
        this.figure.move(dX, dY);
    }

    public boolean checkIfContainsLabel(String label){
        return this.label.contains(label);
    }

    public boolean checkIfContainsPoint(double x,
                                        double y){
        return this.figure.checkIfContainsPoint(x, y);
    }


}
