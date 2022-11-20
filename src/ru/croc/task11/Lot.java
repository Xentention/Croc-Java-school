package ru.croc.task11;

import java.time.LocalDateTime;

public class Lot{
    private volatile int currentPrice = 0;
    private volatile String currentBuyer = "";
    private final LocalDateTime endOfAuction;

    public Lot(int startingPrice,
               LocalDateTime endOfAuction) {
        this.currentPrice = startingPrice;
        this.endOfAuction = endOfAuction;
    }

    // задается новая ставка
    public synchronized void getNewBid(int newPrice,
                                       String buyerName) {
        if (LocalDateTime.now().isBefore(endOfAuction)
                && newPrice > currentPrice) {
            this.currentPrice = newPrice;
            this.currentBuyer = buyerName;
        }
    }

    public String showWinner() {
        if (LocalDateTime.now().isBefore(endOfAuction))
            return "Торги не завершены";
        else
            return "Победитель: " + currentBuyer;
    }
}
