package ua.knu.csc.core.thread;

import ua.knu.csc.core.Storage;

import ua.knu.csc.core.entity.Item;
import ua.knu.csc.core.entity.Truck;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer extends Thread {
    private final Storage storage;
    private final Truck truck;

    private final AtomicBoolean flag1;
    private final AtomicBoolean flag2;

    private final AtomicInteger turn;

    public Consumer(String name, Storage storage, Truck truck, AtomicBoolean flag1, AtomicBoolean flag2, AtomicInteger turn) {
        super(name);

        if (storage == null) {
            throw new NullPointerException("The specified storage is null.");
        }

        if (truck == null) {
            throw new NullPointerException("The specified truck is null.");
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
        this.truck = truck;

        this.flag1 = flag1;
        this.flag2 = flag2;

        this.turn = turn;
    }

    @Override
    public void run() {
        int totalCost = 0;

        Item item;
        while (!isInterrupted() && !truck.isFull()) {
            while (true) {
                flag2.set(true);
                turn.set(1);

                while (flag1.get() && (turn.get() == 1)) { // busy waiting
                    Thread.yield();
                }

                // enter the critical section

                if (!storage.isEmpty()) {
                    item = storage.getItem();

                    flag2.set(false); // exit the critical section
                    break;
                }

                flag2.set(false); // exit the critical section
            }

            if (item == null) {
                break;
            }

            totalCost += item.getCost();
            System.out.println("[Counter]: " + Thread.currentThread().getName() + " counted an item " + item + ".\n"
                    + "[Counter]: The current total cost is " + totalCost + ".\n");

            truck.addItem(item);
        }

        System.out.println("The total cost is " + totalCost + ".\n");
    }
}
