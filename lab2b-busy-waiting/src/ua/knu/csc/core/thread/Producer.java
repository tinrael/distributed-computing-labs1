package ua.knu.csc.core.thread;

import ua.knu.csc.core.entity.Item;
import ua.knu.csc.core.Storage;
import ua.knu.csc.core.entity.Warehouse;

public class Producer extends Thread {
    private final Storage storage;

    private final Warehouse warehouse;

    public Producer(String name, Storage storage, Warehouse warehouse) {
        super(name);

        if (storage == null) {
            throw new NullPointerException("The specified storage is null.");
        }

        if (warehouse == null) {
            throw new NullPointerException("The specified warehouse is null.");
        }

        this.storage = storage;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        Item item;
        while (!isInterrupted() && !warehouse.isEmpty()) {
            item = warehouse.getItem();
            storage.addItem(item);
        }

        storage.addItem(null);
    }
}
