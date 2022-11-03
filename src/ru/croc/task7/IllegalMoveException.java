package ru.croc.task7;

public class IllegalMoveException extends Exception {
    String from;
    String to;

    public IllegalMoveException(){
        // пустое тело
    }

    public IllegalMoveException(String message){
        super(message);
    }

    public IllegalMoveException(String message,
                                String from,
                                String to){
        super(message);
        this.from = from;
        this.to = to;
    }

    public IllegalMoveException(String message,
                                String from,
                                String to,
                                Throwable cause){
        super(message, cause);
        this.from = from;
        this.to = to;
    }

    public IllegalMoveException(String message,
                                Throwable cause){
        super(message, cause);
    }

    public String getMessage(){
        if(from != null && to != null)
            return super.getMessage() + from + " -> " + to;
        else
            return super.getMessage();
    }
}
