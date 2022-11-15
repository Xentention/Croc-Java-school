package ru.croc.task10;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Callable;

public class MD5Hashing implements Callable<String> {
    private static final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private static final char[] ENGLISH_LETTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private String hash;
    private int passwordLength;
    private long checkFrom;
    private long checkTo;


    public MD5Hashing(String hash,
                      int passwordLength,
                      long checkFrom,
                      long checkTo){
        this.hash = hash;
        this.passwordLength = passwordLength;
        this.checkFrom = checkFrom;
        this.checkTo = checkTo;

    }

    // брутфорсом подбирает пароль к хэшу в определенном диапазоне
      @Override
    public String call() throws CannotDecodeExc {
        do {
            int[] combination = getLettersIndFromDecimalNum(checkFrom, passwordLength);
            StringBuilder possiblePassword = new StringBuilder();
            for (int ind :
                    combination) {
                possiblePassword.append(ENGLISH_LETTERS[ind]);
            }
            if (hash.equals(hashPassword(possiblePassword.toString())))
            {   System.out.println("not null");
                return possiblePassword.toString();}
            this.checkFrom++;

        } while (checkFrom < checkTo);
            System.out.println("null");
          return null;
    }

    public static String hashPassword(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        return toHexString(bytes);
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(HEX_DIGITS[(b & 0xff) >> 4]);
            hex.append(HEX_DIGITS[b & 0x0f]);
        }
        return hex.toString();
    }

    // преобразует номер комбинации в массив индексов ENGLISH_LETTERS
    private int[] getLettersIndFromDecimalNum(long num,
                                              int length) {
        int[] lettersInd = new int[length];
        int i = length - 1;
        while (num > 26) {
            long mod = num;
            lettersInd[i] = (int) (mod % 26);
            --i;
            num /= 26;
        }
        return lettersInd;
    }

}
