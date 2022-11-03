package ru.croc.task7;

import static java.lang.Math.abs;

public class ChessKnight extends ChessPiece {

    public ChessKnight(String position) throws IllegalPositionException {
        this.position = ChessPosition.parse(position);
    }

    @Override
    protected boolean isACorrectMove(int x,
                                     int y) {
        return (abs(x - position.x) == 2 && abs(y - position.y) == 1 ||
                abs(x - position.x) == 1 && abs(y - position.y) == 2);
    }

}
