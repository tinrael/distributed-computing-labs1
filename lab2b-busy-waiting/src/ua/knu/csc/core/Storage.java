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

    public void addItem(Item item) {
        items.addLast(item);
        size++;

        System.out.println("[Storage]: " + Thread.currentThread().getName() + " added an item " + item + " to the storage " + name + ".\n"
                + "[Storage]: Current number of items in the storage " + name + ": " + size + ".\n");
    }

    public Item getItem() {
        Item item = items.removeFirst();
        size--;

        System.out.println("[Storage]: " + Thread.currentThread().getName() + " got an item " + item + " from the storage " + name + ".\n"
                + "[Storage]: Current number of items in the storage " + name + ": " + size + ".\n");

        return item;
    }

    public String getName() {
        return name;
    }
}
