package ru.croc.task7;

public class IllegalPositionException extends Exception {
    String incorrectPos;
    public IllegalPositionException(){
        // пустое тело
    }


    public IllegalPositionException(String message){
        super(message);

    }

    public IllegalPositionException(String message,
                                    String incorrectPos){
        super(message);
        this.incorrectPos = incorrectPos;

    }

    public IllegalPositionException(String message,
                                    Throwable cause){
        super(message, cause);
    }

    public IllegalPositionException(String message,
                                    String incorrectPos,
                                    Throwable cause){
        super(message, cause);
        this.incorrectPos = incorrectPos;
    }

    public String getMessage(){
        if(incorrectPos != null)
            return super.getMessage() + incorrectPos;
        else
            return super.getMessage();
    }
}
