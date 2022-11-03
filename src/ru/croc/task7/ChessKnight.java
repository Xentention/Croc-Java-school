package ru.croc.task7;

import static java.lang.Math.abs;

public class ChessKnight extends ChessPiece {

    public ChessKnight(String position) throws IllegalPositionException {
        this.position = ChessPosition.parse(position);
    }

    @Override
    public void moveTo(String newPos) throws IllegalMoveException {
        ChessPosition temp;
        try {
            temp = ChessPosition.parse(position.toString());
            temp.changePos(newPos);
        }
        catch (IllegalMoveException | IllegalPositionException exc){
            throw new IllegalMoveException(exc.getMessage(), exc);
        }
        if(isACorrectMove(temp.x, temp.y))
            this.position = temp;
        else
            throw new IllegalMoveException("Конь так не ходит: ", position.toString(), newPos);
    }

    @Override
    protected boolean isACorrectMove(int x,
                                     int y) {
        return (abs(x - position.x) == 2 && abs(y - position.y) == 1 ||
                abs(x - position.x) == 1 && abs(y - position.y) == 2);
    }

}
