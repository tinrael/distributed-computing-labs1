package ua.knu.csc;

import ua.knu.csc.core.Storage;

import ua.knu.csc.core.entity.Truck;
import ua.knu.csc.core.entity.Warehouse;

import ua.knu.csc.core.thread.Consumer;
import ua.knu.csc.core.thread.Producer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();

        // TODO: repair the issue below.
        // The warehouse's size and truck's capacity must be equal.
        final int NUMBER_OF_ITEMS = 10;

        Warehouse warehouse = new Warehouse(NUMBER_OF_ITEMS);
        Truck truck = new Truck(NUMBER_OF_ITEMS);

        Producer producer = new Producer("Producer", storage, warehouse);
        Consumer consumer = new Consumer("Consumer", storage, truck);

        System.out.println("Number of items in the WAREHOUSE: " + warehouse.getSize());
        System.out.println("Number of items in the TRUCK: " + truck.getSize());
        System.out.println();

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Number of items in the TRUCK: " + truck.getSize());
    }
}
