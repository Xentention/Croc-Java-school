package ru.croc.task5;

public abstract class Figure {
    protected FigureTypes type;
    protected String label;
}

enum FigureTypes {
    CIRCLE,
    RECTANGLE
}
