package ru.croc.task7;

public class ChessPosition {
    protected int x;
    protected int y;
    protected static final String[] X_AS_LETTER = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};

    public ChessPosition(int x,
                         int y) throws IllegalPositionException {
        if(!checkIfXYCorrect(x, y)){
            throw new IllegalPositionException("Несуществующая на поле клетка: ",
                                                "x = " + x + ", y = " + y);
        }
        this.x = x;
        this.y = y - 1;
    }

    public static ChessPosition parse(String pos) throws IllegalPositionException {
        if(pos.length() != 2)
            throw new IllegalPositionException("Некорректный формат строки: ", pos);

        int x;
        int y;

        x = pos.charAt(0) - 'a';
        try{
            y = Integer.parseInt(String.valueOf(pos.charAt(1)));
        }
        catch (NumberFormatException numFormatExc) {
            throw new IllegalPositionException("Некорректно заданная позиция: ", pos, numFormatExc);
        }

        if(!checkIfXYCorrect(x, y)){
            throw new IllegalPositionException("Некорректно заданная позиция: ", pos);
        }

        return new ChessPosition(x, y);
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

    private static boolean checkIfXYCorrect(int x,
                                            int y) {
        return (x >= 0 && x <= 7) && (y >= 1 && y <= 8);
    }

}
