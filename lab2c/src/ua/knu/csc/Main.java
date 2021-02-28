package ua.knu.csc;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        final int NUMBER_OF_FIGHTERS = 7;

        int[] fighters = new int[NUMBER_OF_FIGHTERS];

        fighters[0] = 10;
        fighters[1] = 50;
        fighters[2] = 20;
        fighters[3] = 90;
        fighters[4] = 70;
        fighters[5] = 30;
        fighters[6] = 40;

        Arena arena = new Arena(fighters, 0, fighters.length);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(arena);

        System.out.println("Champion: " + result + ".");
    }
}
