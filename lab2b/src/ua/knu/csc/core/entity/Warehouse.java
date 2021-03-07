package ua.knu.csc.core.entity;

import java.util.NoSuchElementException;

public class Warehouse {
    private int size;

    public Warehouse(int size) {
        this.size = size;
    }

    public Item getItem() {
        if (size > 0) {
            Item item = new Item("item" + size, size);
            size--;

            System.out.println("[Warehouse]: " + Thread.currentThread().getName() + " got an item " + item + " from the warehouse.\n");

            return item;
        }

        throw new NoSuchElementException("The warehouse is empty.");
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}
