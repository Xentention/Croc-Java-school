package ru.croc.task10;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Solution {
    private static final int PASSWORD_LENGTH = 7;
    private static final char[] ENGLISH_LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    // в несколько потоков перебором подбирает к хэшу пароль определенной длины
    public static String calculatePassword(int threadsNumber,
                                           String someStringHash) {

        // интервалы, в которых будут выполняться потоки
        long[] threadPoints = getArrayOfSplittingThreadPoints(threadsNumber);
        // пул всех выполняемых нами потоков
        ExecutorService threadPool = Executors.newFixedThreadPool(threadsNumber);
        ArrayList<Future<String>> results = new ArrayList<>();
        for(int i = 0; i < threadsNumber; ++i) {
            results.add(threadPool.submit(new MD5Hashing(someStringHash, PASSWORD_LENGTH,
                                                         threadPoints[i], threadPoints[i+1])));
        }

        for (Future<String> result
                : results){
            try {
                String res = result.get();
                threadPool.shutdownNow();
                return res;
            } catch (InterruptedException | ExecutionException e){
                // ignore
            }
        }
        threadPool.shutdown();
        return "Could not decode the hash";

    }

    // разбиваем код на интервалы, с которыми работаем
    private static long[] getArrayOfSplittingThreadPoints(int threadsNumber){
        long[] threadPoints = new long[threadsNumber + 1];
        long interval = (long) (Math.pow(ENGLISH_LETTERS.length, PASSWORD_LENGTH) / threadsNumber);
        for (int i = 0; i < threadPoints.length - 1; ++i){
            threadPoints[i] = i * interval;
        }
        threadPoints[threadPoints.length - 1] = (long) Math.pow(ENGLISH_LETTERS.length, PASSWORD_LENGTH);
        return threadPoints;
    }
}
