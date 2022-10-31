package ru.croc.task7;

import static java.lang.Math.abs;

public class ChessKnight implements ChessPiece {
    protected ChessPosition position;

    public ChessKnight(String position) throws IllegalPositionException {
        this.position = ChessPosition.parse(position);
    }

    public ChessKnight(int x,
                       int y) throws IllegalPositionException {
        this.position = new ChessPosition(x, y);
    }

    @Override
    public void moveTo(int x,
                       int y) throws IllegalMoveException {
        try {
            if(abs(x - position.x) == 2 && abs(y - position.y) == 1 ||
                    abs(x - position.x) == 1 && abs(y - position.y) == 2)
                position.changePos(x, y);
            else
                throw new IllegalMoveException("Конь так не ходит");
        }
        catch (IllegalMoveException IMExc){
            throw new IllegalMoveException("Конь так не ходит", IMExc);
        }
    }

    @Override
    public void moveTo(String newPos) throws IllegalMoveException {
        ChessPosition temp;
        try {
            temp = ChessPosition.parse(position.toString());
            temp.changePos(newPos);
        }
        catch (IllegalMoveException IMExc){
            throw new IllegalMoveException("Конь так не ходит", IMExc);
        } catch (IllegalPositionException IPExc) {
            throw new IllegalMoveException(IPExc.getMessage(), IPExc);
        }
        if(abs(temp.x - position.x) == 2 && abs(temp.y - position.y) == 1 ||
        abs(temp.x - position.x) == 1 && abs(temp.y - position.y) == 2)
            this.position = temp;
        else
            throw new IllegalMoveException("Конь так не ходит");


    }
}
