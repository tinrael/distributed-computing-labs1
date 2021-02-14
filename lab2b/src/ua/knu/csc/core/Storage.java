package ua.knu.csc.core;

import java.util.LinkedList;

public class Storage {
    private final int CAPACITY = 10;
    private int size = 0;

    private final LinkedList<Item> items = new LinkedList<>();

    public synchronized void addItem(Item item) throws InterruptedException {
        while (size == CAPACITY) {
            wait();
        }

        items.addLast(item);
        size++;

        notify();
    }

    public synchronized Item getItem() throws InterruptedException {
        while (size == 0) {
            wait();
        }

        Item item = items.removeFirst();
        size--;

        notify();

        return item;
    }
}
