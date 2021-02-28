package ua.knu.csc;

import ua.knu.csc.entity.Monk;

import java.util.concurrent.RecursiveTask;

public class Arena extends RecursiveTask<Monk> {
    private final Monk[] fighters;

    private final int start;
    private final int end;

    public Arena(Monk[] fighters, int start, int end) {
        if (start == end) {
            throw new IllegalArgumentException("No elements in the specified range [start, end).");
        }

        if (end < start) {
            throw new IllegalArgumentException("The specified 'end' index is less than the specified 'start' index.");
        }

        this.fighters = fighters;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Monk compute() {
        int length = end - start;

        if (length == 1) {
            Monk winner = fighters[start];

            System.out.println("Arena: No pair to [" + winner.getStrength() +"]. Winner: [" + winner.getStrength() + "].");

            return winner;
        }

        if (length == 2) {
            Monk winner;

            if (fighters[start].getStrength() > fighters[start + 1].getStrength()) {
                winner = fighters[start];
            } else {
                winner = fighters[start + 1];
            }

            System.out.println("Arena: [" + fighters[start].getStrength() + "] vs [" + fighters[start + 1].getStrength() + "]. Winner: [" + winner.getStrength() + "].");

            return winner;
        }

        int middle = (start + end) / 2;

        Arena leftArena = new Arena(fighters, start, middle);
        Arena rightArena = new Arena(fighters, middle, end);

        leftArena.fork();
        rightArena.fork();

        Monk leftArenaWinner = leftArena.join();
        Monk rightArenaWinner = rightArena.join();

        Monk winner;

        if (leftArenaWinner.getStrength() > rightArenaWinner.getStrength()) {
            winner = leftArenaWinner;
        } else {
            winner = rightArenaWinner;
        }

        System.out.println("Arena: [" + leftArenaWinner.getStrength() + "] vs [" + rightArenaWinner.getStrength() + "]. Winner: [" + winner.getStrength() + "].");

        return winner;
    }
}
