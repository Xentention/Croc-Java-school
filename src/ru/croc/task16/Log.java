package ru.croc.task16;

public record Log(Long time, String message) {

    public String toString() {
        return time + " " + message;
    }
}
