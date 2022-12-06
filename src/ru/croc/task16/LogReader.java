package ru.croc.task16;

import java.io.*;

public class LogReader {
    private final BufferedReader in;
    private final File file;

    static class FileEndedExc extends Exception {   }

    public LogReader(File file) throws FileNotFoundException {
        this.file = file;
        this.in = new BufferedReader(new FileReader(file));
    }

    /**
     * Reads a next Log from a file
     *
     * @return new Log or null if the end of the stream
     * has been reached without reading any characters
     * @throws FileEndedExc if the file has ended. Closes a BufferedReader.
     * @throws CannotParseLogsExc signals that a file cannot be parsed
     *                            correctly for some reason
     */
    public Log readNext() throws CannotParseLogsExc, FileEndedExc {
        try {
            String line = in.readLine();
            if (line == null) {
                in.close();
                throw new FileEndedExc();
            }
            String[] split = line.split(" ");
            return new Log(Long.parseLong(split[0]), split[1]);
        } catch (IOException | NumberFormatException e) {
            throw new CannotParseLogsExc(e, file);
        }

    }

}