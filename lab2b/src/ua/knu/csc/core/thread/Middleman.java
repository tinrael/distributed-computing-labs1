package ua.knu.csc.core.thread;

import ua.knu.csc.core.Storage;
import ua.knu.csc.core.entity.Item;

public class Middleman extends Thread {
    private final Storage source;
    private final Storage target;

    public Middleman(String name, Storage source, Storage target) {
        super(name);

        if (source == null) {
            throw new NullPointerException("The specified 'source' storage is null.");
        }

        if (target == null) {
            throw new NullPointerException("The specified 'target' storage is null.");
        }

        this.source = source;
        this.target = target;
    }

    @Override
    public void run() {
        Item item;
        while (!isInterrupted()) {
            item = source.getItem();
            target.addItem(item);
            if (item == null) {
                break;
            }
        }
    }
}
