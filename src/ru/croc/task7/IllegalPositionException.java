package ru.croc.task7;

public class IllegalPositionException extends Exception {
    public IllegalPositionException(){
        // пустое тело
    }
    public IllegalPositionException(String message){
        super(message);
    }
    public IllegalPositionException(String message,
                                    Throwable cause){
        super(message, cause);
    }
}
