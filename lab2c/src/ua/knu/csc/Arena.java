package ua.knu.csc;

import java.util.concurrent.RecursiveTask;

public class Arena extends RecursiveTask<Integer> {
    private final int[] fighters;

    private final int start;
    private final int end;

    public Arena(int[] fighters, int start, int end) {
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
    protected Integer compute() {
        int length = end - start;

        if (length == 1) {
            int winner = fighters[start];

            System.out.println("[Arena]: No pair to [" + winner +"]. Winner: [" + winner + "].");

            return winner;
        }

        if (length == 2) {
            int winner = Math.max(fighters[start], fighters[start + 1]);

            System.out.println("[Arena]: [" + fighters[start] + "] vs [" + fighters[start + 1] + "]. Winner: [" + winner + "].");

            return winner;
        }

        int middle = (start + end) / 2;

        Arena leftArena = new Arena(fighters, start, middle);
        Arena rightArena = new Arena(fighters, middle, end);

        leftArena.fork();
        rightArena.fork();

        int leftArenaWinner = leftArena.join();
        int rightArenaWinner = rightArena.join();

        int winner = Math.max(leftArenaWinner, rightArenaWinner);

        System.out.println("[Arena]: [" + leftArenaWinner + "] vs [" + rightArenaWinner + "]. Winner: [" + winner + "].");

        return winner;
    }
}
