package ru.croc.task5;


public class Annotation {
    protected Figure figure;
    protected String label;

    public Annotation(Figure figure, String label){
        this.figure = figure;
        this.label = label;
    }

    public String toString(){
        return this.figure.toString() + ": " + label;
    }

}
