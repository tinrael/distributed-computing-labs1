package ua.knu.csc;

import ua.knu.csc.entity.Monk;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        final int NUMBER_OF_FIGHTERS = 7;

        Monk[] fighters = new Monk[NUMBER_OF_FIGHTERS];

        fighters[0] = new Monk("monk0", 10, "monastery0");
        fighters[1] = new Monk("monk1", 50, "monastery1");
        fighters[2] = new Monk("monk2", 20, "monastery2");
        fighters[3] = new Monk("monk3", 90, "monastery3");
        fighters[4] = new Monk("monk4", 70, "monastery4");
        fighters[5] = new Monk("monk5", 30, "monastery5");
        fighters[6] = new Monk("monk6", 40, "monastery6");

        Arena arena = new Arena(fighters, 0, fighters.length);

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        System.out.println("--- Start ---");
        Monk winner = forkJoinPool.invoke(arena);
        System.out.println("--- End ---");

        System.out.println("Champion: " + winner + ".");
    }
}
