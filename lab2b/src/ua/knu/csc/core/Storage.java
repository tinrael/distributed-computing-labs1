package ua.knu.csc.core;

import ua.knu.csc.core.entity.Item;

import java.util.LinkedList;

public class Storage {
    private final int CAPACITY = 5;
    private int size = 0;

    private final LinkedList<Item> items = new LinkedList<>();

    public synchronized void addItem(Item item) {
        boolean isInterrupted = false;

        while (size == CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                isInterrupted = true;
            }
        }

        items.addLast(item);
        size++;

        System.out.println("[add]: " + Thread.currentThread().getName() + " ADDS an item to the storage.");
        System.out.println("[add]: Current number of items in the storage: " + size);
        System.out.println();

        if (size == 1) {
            notify();
        }

        if (isInterrupted) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized Item getItem() {
        boolean isInterrupted = false;

        while (size == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                isInterrupted = true;
            }
        }

        Item item = items.removeFirst();
        size--;

        System.out.println("[get]: " + Thread.currentThread().getName() + " GETS an item from the storage.");
        System.out.println("[get]: Current number of items in the storage: " + size);
        System.out.println();

        if (size == CAPACITY - 1) {
            notify();
        }

        if (isInterrupted) {
            Thread.currentThread().interrupt();
        }

        return item;
    }
}
