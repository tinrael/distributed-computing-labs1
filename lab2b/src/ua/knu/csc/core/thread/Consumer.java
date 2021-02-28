package ua.knu.csc.core.thread;

import ua.knu.csc.core.entity.Item;
import ua.knu.csc.core.Storage;
import ua.knu.csc.core.entity.Truck;

public class Consumer extends Thread {
    private final Storage storage;

    private final Truck truck;

    public Consumer(String name, Storage storage, Truck truck) {
        super(name);

        if (storage == null) {
            throw new NullPointerException("The specified storage is null.");
        }

        if (truck == null) {
            throw new NullPointerException("The specified truck is null.");
        }

        this.storage = storage;
        this.truck = truck;
    }

    @Override
    public void run() {
        Item item;
        while (!isInterrupted() && !truck.isFull()) {
            item = storage.getItem();
            if (item == null) {
                break;
            }
            truck.addItem(item);
        }
    }
}
