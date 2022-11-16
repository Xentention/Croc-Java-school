package ru.croc.task10;

import java.util.ArrayList;

public class Solution {
    private static final int PASSWORD_LENGTH = 7;
    private static final char[] ENGLISH_LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    // в несколько потоков перебором подбирает к хэшу пароль определенной длины
    public static String calculatePassword(int threadsNumber,
                                           String someStringHash) {

        long[] threadPoints = getArrayOfSplittingThreadPoints(threadsNumber);
        MD5Hashing[] threads = new MD5Hashing[threadsNumber];
        ArrayList<String> results = new ArrayList<>();

        for(int i = 0; i < threadsNumber; ++i){
            threads[i] = new MD5Hashing(someStringHash, PASSWORD_LENGTH,
                                        threadPoints[i], threadPoints[i+1]);
            try {
                results.add(threads[i].call());
            } catch (CannotDecodeExc e) {
                // ignore
            }
        }

        if(!results.isEmpty()) {
            return results.get(0);
        }
        return "Could not decode the hash";

    }

    // разбиваем код на интервалы, с которыми работаем
    private static long[] getArrayOfSplittingThreadPoints(int threadsNumber){
        long[] threadPoints = new long[threadsNumber + 1];
        long interval = (long) (Math.pow(ENGLISH_LETTERS.length, PASSWORD_LENGTH) / threadsNumber);
        for (int i = 0; i < threadPoints.length; ++i){
            threadPoints[i] = i * interval;
        }
        return threadPoints;
    }
}
