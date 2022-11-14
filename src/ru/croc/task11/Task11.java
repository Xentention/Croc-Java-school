package ru.croc.task11;

public class Task11 {

    public static void main(String[] args) {
        Lot vase = new Lot(1000);
        vase.start();

        vase.getNewBid(1200, "Mary");
        vase.getNewBid(1300, "Joe");
        vase.getNewBid(1300, "K");
        vase.getNewBid(2000, "Xen");
        vase.getNewBid(10, "Hi");
        vase.getNewBid(2000, "Xen_impostor");
    }
}
