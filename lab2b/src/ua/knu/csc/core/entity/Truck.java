package ua.knu.csc.core.entity;

import java.util.List;
import java.util.ArrayList;

public class Truck {
    private final int capacity;
    private int size = 0;

    private final List<Item> items;

    public Truck(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>(capacity);
    }

    public void addItem(Item item) {
        if (item == null) {
            throw new NullPointerException("The specified item is null.");
        }

        if (size < capacity){
            items.add(item);
            size++;
        }

        throw new IllegalStateException("The truck is full.");
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }
}
