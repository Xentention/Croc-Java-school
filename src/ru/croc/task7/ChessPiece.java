package ru.croc.task7;

public abstract class ChessPiece {
    protected ChessPosition position;
    public void moveTo(String newPos) throws IllegalMoveException{
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
            throw new IllegalMoveException("Фигура так не ходит: ", position.toString(), newPos);
    }
    protected abstract boolean isACorrectMove(int x,
                                              int y);
}
