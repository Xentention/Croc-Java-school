package ru.croc.task7;

public abstract class ChessPiece {
    protected ChessPosition position;
    public abstract void moveTo(String newPos) throws IllegalMoveException;
    protected abstract boolean isACorrectMove(int x,
                                              int y);
}
