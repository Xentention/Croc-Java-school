package ru.croc.task7;

public class ChessPosition {
    protected int x;
    protected int y;
    protected final String[] X_AS_LETTER = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};

    public ChessPosition(int x,
                         int y) throws IllegalPositionException {
        ChessPosition.checkIfXYCorrect(x, y);
        this.x = x;
        this.y = y - 1;
    }

    public static ChessPosition parse(String pos) throws IllegalPositionException {
        if(pos.length() != 2)
            throw new IllegalPositionException("Incorrect string data");

        int x;
        int y;

        x = pos.charAt(0) - 'a';
        try{
            y = Integer.parseInt(String.valueOf(pos.charAt(1)));
        }
        catch (NumberFormatException numFormatExc) {
            throw new IllegalPositionException("The number should exist and be in between 1 and 8", numFormatExc);
        }
        ChessPosition.checkIfXYCorrect(x, y);

        return new ChessPosition(x, y);
    }

    public void changePos(int x,
                          int y) throws IllegalMoveException{
        try {
            ChessPosition.checkIfXYCorrect(x, y);
        } catch (IllegalPositionException IPExc) {
            throw new IllegalMoveException(IPExc.getMessage(), IPExc);
        }
        this.x = x;
        this.y = y - 1;
    }

    public void changePos(String newPos) throws IllegalMoveException {
        ChessPosition temp;
        try {
            temp = parse(newPos);
        } catch (IllegalPositionException IPExc) {
            throw new IllegalMoveException(IPExc.getMessage(), IPExc);
        }
        this.x = temp.x;
        this.y = temp.y;
    }

    public String toString(){
        return X_AS_LETTER[x] + (y + 1);
    }

    private static void checkIfXYCorrect(int x,
                                         int y) throws IllegalPositionException {
        if(x < 0 || x > 7)
            throw new IllegalPositionException("x should be in between a and h");
        if(y < 1 || y > 8)
            throw new IllegalPositionException("y should be in between 1 and 8");
    }

}
