package ru.croc.task6;

public abstract class Figure implements Movable {
    protected FigureTypes type;
    protected String label;

}

enum FigureTypes {
    CIRCLE,
    RECTANGLE
}
