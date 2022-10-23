package ru.croc.task3;

import java.util.Scanner;

public class Task3 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int[] intarr = fillArray(in);

        int whichswap;
        whichswap = findIndexMax(intarr);
        swapInIntArray(intarr, whichswap, (intarr.length - 1));

        whichswap = findIndexMin(intarr);
        swapInIntArray(intarr, whichswap, 0);

        System.out.print("Результат: ");
        for (int i : intarr) System.out.print(i + " ");
        in.close();
    }

    static int[] fillArray(Scanner in){
        System.out.println("Введите массив чисел:");
        String tempStr = in.nextLine();
        //Разбиваем полученную строку на слова
        String[] subtempStr = tempStr.split(" ");
        //Создаем массив для получаемых целочисленных значений
        int[] numbers = new int[subtempStr.length];
        //Получаем из строк числа
        for(int i = 0; i < subtempStr.length; ++i){
            try{
                numbers[i] = Integer.parseInt(subtempStr[i]);
            }
            //обрабатываем строки, которые невозможно привести к int
            catch (NumberFormatException numFormatE) {
                System.out.println(numFormatE.getMessage() + " невозможно получить int. " +
                        "Результат может оказаться некорректным.");
                //удаляем из строки все не-цифры
                subtempStr[i] = subtempStr[i].replaceAll("\\D+", "");
                //Вместо пустой подстроки оставляем 0
                if(subtempStr[i].equals("")) subtempStr[i]="0";
                i--;
            }
        }
        return numbers;
    }

    static int findIndexMax(int[] arr){
        int answ = 0;
        for(int i = 0; i < arr.length; ++i)
            if(arr[i] > arr[answ]) answ = i;
        return answ;
    }

    static int findIndexMin(int[] arr){
        int answ = 0;
        for(int i = 0; i < arr.length; ++i)
            if(arr[i] < arr[answ]) answ = i;
        return answ;
    }

    static void swapInIntArray(int[] arr, int firstindx, int secondindx){
        int temp = arr[firstindx];
        arr[firstindx] = arr[secondindx];
        arr[secondindx] = temp;
    }

}
