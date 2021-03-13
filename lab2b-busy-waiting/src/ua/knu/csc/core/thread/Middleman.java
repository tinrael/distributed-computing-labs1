package ua.knu.csc.core.thread;

import ua.knu.csc.core.Storage;
import ua.knu.csc.core.entity.Item;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Middleman extends Thread {
    private final Storage source;

    private final AtomicBoolean sourceFlag1;
    private final AtomicBoolean sourceFlag2;

    private final AtomicInteger sourceTurn;

    private final Storage target;

    private final AtomicBoolean targetFlag1;
    private final AtomicBoolean targetFlag2;

    private final AtomicInteger targetTurn;

    public Middleman(String name, Storage source, AtomicBoolean sourceFlag1, AtomicBoolean sourceFlag2, AtomicInteger sourceTurn, Storage target, AtomicBoolean targetFlag1, AtomicBoolean targetFlag2, AtomicInteger targetTurn) {
        super(name);

        if (source == null) {
            throw new NullPointerException("The specified 'source' storage is null.");
        }

        if (sourceFlag1 == null) {
            throw new NullPointerException("The specified 'sourceFlag1' flag is null.");
        }

        if (sourceFlag2 == null) {
            throw new NullPointerException("The specified 'sourceFlag2' flag is null.");
        }

        if (sourceTurn == null) {
            throw new NullPointerException("The specified 'sourceTurn' is null.");
        }

        if (target == null) {
            throw new NullPointerException("The specified 'target' storage is null.");
        }

        if (targetFlag1 == null) {
            throw new NullPointerException("The specified 'targetFlag1' flag is null.");
        }

        if (targetFlag2 == null) {
            throw new NullPointerException("The specified 'targetFlag2' flag is null.");
        }

        if (targetTurn == null) {
            throw new NullPointerException("The specified 'targetTurn' is null.");
        }

        this.source = source;

        this.sourceFlag1 = sourceFlag1;
        this.sourceFlag2 = sourceFlag2;

        this.sourceTurn = sourceTurn;

        this.target = target;

        this.targetFlag1 = targetFlag1;
        this.targetFlag2 = targetFlag2;

        this.targetTurn = targetTurn;
    }

    /* Peterson's solution is implemented below.
     * Peterson's solution meets CS (critical sections) requirements and only works if assignments are atomic.
     * Peterson's solution has poor performance.
     */
    @Override
    public void run() {
        Item item;
        while (!isInterrupted()) {
            // get an item
            while (true) {
                sourceFlag2.set(true);
                sourceTurn.set(1);

                while (sourceFlag1.get() && (sourceTurn.get() == 1)) { // busy waiting
                    Thread.yield();
                }

                // enter the critical section

                if (!source.isEmpty()) {
                    item = source.getItem();

                    sourceFlag2.set(false); // exit the critical section
                    break;
                }

                sourceFlag2.set(false); // exit the critical section
            }

            // add an item
            while (true) {
                targetFlag1.set(true);
                targetTurn.set(2);

                while (targetFlag2.get() && (targetTurn.get() == 2)) { // busy waiting
                    Thread.yield();
                }

                // enter the critical section

                if (!target.isFull()) {
                    target.addItem(item);

                    targetFlag1.set(false); // exit the critical section
                    break;
                }

                targetFlag1.set(false); // exit the critical section
            }

            if (item == null) {
                break;
            }
        }
    }
}
