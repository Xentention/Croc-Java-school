package ru.croc.task6;

public class ObjectNotFoundExc extends Exception{
    public ObjectNotFoundExc(){
        // пустое тело
    }
    public ObjectNotFoundExc(String message){
        super(message);
    }
    public ObjectNotFoundExc(String message,
                             Throwable cause){
        super(message, cause);
    }
}
