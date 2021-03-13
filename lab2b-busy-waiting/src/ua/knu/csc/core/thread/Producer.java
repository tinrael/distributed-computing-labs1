package ua.knu.csc.core.thread;

import ua.knu.csc.core.entity.Item;
import ua.knu.csc.core.entity.Warehouse;

import ua.knu.csc.core.Storage;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends Thread {
    private final Storage storage;
    private final Warehouse warehouse;

    private final AtomicBoolean flag1;
    private final AtomicBoolean flag2;

    private final AtomicInteger turn;

    public Producer(String name, Storage storage, Warehouse warehouse, AtomicBoolean flag1, AtomicBoolean flag2, AtomicInteger turn) {
        super(name);

        if (storage == null) {
            throw new NullPointerException("The specified storage is null.");
        }

        if (warehouse == null) {
            throw new NullPointerException("The specified warehouse is null.");
        }

        if (flag1 == null) {
            throw new NullPointerException("The specified 'flag1' flag is null.");
        }

        if (flag2 == null) {
            throw new NullPointerException("The specified 'flag2' flag is null.");
        }

        if (turn == null) {
            throw new NullPointerException("The specified 'turn' is null.");
        }

        this.storage = storage;
        this.warehouse = warehouse;

        this.flag1 = flag1;
        this.flag2 = flag2;

        this.turn = turn;
    }

    @Override
    public void run() {
        Item item;
        while (!isInterrupted() && !warehouse.isEmpty()) {
            item = warehouse.getItem();

            while (true) {
                flag1.set(true);
                turn.set(2);

                while (flag2.get() && (turn.get() == 2)) { // busy waiting
                    Thread.yield();
                }

                // enter the critical section

                if (!storage.isFull()) {
                    storage.addItem(item);

                    flag1.set(false); // exit the critical section
                    break;
                }

                flag1.set(false); // exit the critical section
            }
        }

        while (true) {
            flag1.set(true);
            turn.set(2);

            while (flag2.get() && (turn.get() == 2)) { // busy waiting
                Thread.yield();
            }

            // enter the critical section

            if (!storage.isFull()) {
                storage.addItem(null);

                flag1.set(false); // exit the critical section
                break;
            }

            flag1.set(false); // exit the critical section
        }
    }
}
