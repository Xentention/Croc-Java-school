package ru.croc.task7;

public interface ChessPiece {
    void moveTo(int x, int y) throws IllegalMoveException;
    void moveTo(String newPos) throws IllegalMoveException;
}
