package ru.croc.task10.tests;

import org.testng.annotations.Test;
import ru.croc.task10.Solution;

import java.util.concurrent.ExecutionException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;
import static ru.croc.task10.MD5Hashing.hashPassword;


public class PasswordHashTest {

    private static final String INITIAL_HASH = "40682260CC011947FC2D0B1A927138C5";


    @Test
    private void positiveMainCaseTest() throws ExecutionException, InterruptedException {
        int threadsNumber = 4;

        String password = Solution.calculatePassword(threadsNumber, INITIAL_HASH);
        String hash = hashPassword(password);

        assertEquals(hash, INITIAL_HASH);
    }


    @Test
    private void negativeMainCaseTest() throws ExecutionException, InterruptedException {
        int threadsNumber = 4;

        String someStrForTest = "smsmbls";
        String someStringHash = hashPassword(someStrForTest);
        String password = Solution.calculatePassword(threadsNumber, someStringHash);
        String recalculatedHash = hashPassword(password);

        assertNotSame(recalculatedHash, INITIAL_HASH);
    }

}