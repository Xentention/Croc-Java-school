package ru.croc.task7;

public class IllegalMoveException extends Exception {
    public IllegalMoveException(){
        // пустое тело
    }
    public IllegalMoveException(String message){
        super(message);
    }
    public IllegalMoveException(String message,
                                Throwable cause){
        super(message, cause);
    }
}
