package ru.croc.task19;

import java.io.*;

public class Task19 {
    public static void main(String[] args) {
        fileHelloWorld();
    }

    public static void fileHelloWorld(){
        String path = "src/ru/croc/task19/output/helloWorld.txt";
        File output = new File(path);

        if(!output.exists()) {
            try {
                output.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("Hello, World!");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
