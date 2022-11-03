package ru.croc.task7;

public class Task7 {
    public static void main(String[] args){
        // порядок ходов фигуры
        String[] commands = {"b2", "d3", "c5", "b7"};

        ChessKnight whiteKnight;
        try {
            whiteKnight = new ChessKnight(commands[0]);
        } catch (IllegalPositionException IPExc) {
            System.out.println(IPExc.getMessage());
            return;
        }

        for (int i = 1; i < commands.length; ++i) {
            try {
                whiteKnight.moveTo(commands[i]);
            } catch (IllegalMoveException IMExc) {
                System.out.println(IMExc.getMessage());
                return;
            }
        }

        System.out.println("OK");
    }

}
