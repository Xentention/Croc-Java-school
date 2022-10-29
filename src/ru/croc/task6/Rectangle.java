package ru.croc.task6;

public class Rectangle extends Figure {
    protected double[] LeftBottom = new double[2];
    protected double[] RightTop = new double[2];

    public Rectangle(double x1,
                     double y1,
                     double x2,
                     double y2,
                     String label){
        type = FigureTypes.RECTANGLE;
        setLeftBottom(x1, y1);
        setRightTop(x2, y2);
        this.label = label;

    }

    public void move(double dX,
                     double dY){
        setLeftBottom(LeftBottom[0]+dX,
                    LeftBottom[1]+dY);
        setRightTop(RightTop[0]+dX,
                    RightTop[1]+dY);
    }

    public void setLeftBottom(double x,
                              double y) {
        this.LeftBottom[0] = x;
        this.LeftBottom[1] = y;
    }

    public void setRightTop(double x,
                            double y) {
        this.RightTop[0] = x;
        this.RightTop[1] = y;
    }
}
