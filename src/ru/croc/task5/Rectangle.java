package ru.croc.task5;

public class Rectangle extends Figure{
    protected double[] LeftBottom = new double[2];
    protected double[] RightTop = new double[2];

    public Rectangle(double x1,
                     double y1,
                     double x2,
                     double y2){
        setLeftBottom(x1, y1);
        setRightTop(x2, y2);

    }

    public String toString(){
        return "R (" + this.LeftBottom[0] + ", " + this.LeftBottom[1] + "), "
                + "(" + this.RightTop[0] + ", " + this.RightTop[1] + ")";
    }

    protected void setLeftBottom(double x,
                              double y) {
        this.LeftBottom[0] = x;
        this.LeftBottom[1] = y;
    }

    protected void setRightTop(double x,
                            double y) {
        this.RightTop[0] = x;
        this.RightTop[1] = y;
    }
}
