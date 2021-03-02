package ua.knu.csc.core;

import ua.knu.csc.core.entity.Item;

import java.util.LinkedList;

public class Storage {
    private final String name;

    private final int CAPACITY = 5;
    private int size = 0;

    private final LinkedList<Item> items = new LinkedList<>();

    public Storage(String name) {
        if (name == null) {
            throw new NullPointerException("The specified name is null.");
        }

        this.name = name;
    }

    // This method guarantees that an item will be added, even if the thread is interrupted.
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

        System.out.println("[Storage]: " + Thread.currentThread().getName() + " added an item " + item + " to the storage " + name + ".\n"
                + "[Storage]: Current number of items in the storage " + name + ": " + size + ".\n");

        if (size == 1) {
            notify();
        }

        if (isInterrupted) {
            Thread.currentThread().interrupt();
        }
    }

    // This method guarantees that an item will be got, even if the thread is interrupted.
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

        System.out.println("[Storage]: " + Thread.currentThread().getName() + " got an item " + item + " from the storage " + name + ".\n"
                + "[Storage]: Current number of items in the storage " + name + ": " + size + ".\n");

        if (size == CAPACITY - 1) {
            notify();
        }

        if (isInterrupted) {
            Thread.currentThread().interrupt();
        }

        return item;
    }

    /* final fields, which cannot be modified after the object is constructed,
     * can be safely read through non-synchronized methods, once the object is constructed.
     */
    public String getName() {
        return name;
    }
}
