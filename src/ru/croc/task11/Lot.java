package ru.croc.task11;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalDateTime.now;

public class Lot extends Thread{
    private int currentPrice;
    private String currentBuyer;
    private LocalDateTime currentTime;
    private LocalDateTime timeOfSale;

    public Lot(int startingPrice) {
        this.currentPrice = startingPrice;
        this.currentTime = now();
    }


    @Override
    public void run() {
        // вообще должно быть 10 минут, но время уменьшено в целях наглядности
        while (now().isBefore(currentTime.plusSeconds(10))){
            // пустое тело таймера
        }
        this.interrupt();
        timeOfSale = now();
        System.out.println(showWinner());

    }

    public synchronized void getNewBid(int newPrice,
                                       String buyerName) {
        if (newPrice > currentPrice) {
            this.currentPrice = newPrice;
            this.currentBuyer = buyerName;
            this.currentTime = now();
        }
    }

    public String showWinner(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        if(timeOfSale != null)
            return "Победитель: " + currentBuyer + "\nВремя покупки: " + timeOfSale.format(formatter)
                    + "\nЦена продажи: " + currentPrice;
        else
            return "Торги не завершены";
    }
}
