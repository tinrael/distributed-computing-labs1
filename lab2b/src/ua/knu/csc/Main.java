package ua.knu.csc;

import ua.knu.csc.core.Storage;

import ua.knu.csc.core.entity.Truck;
import ua.knu.csc.core.entity.Warehouse;

import ua.knu.csc.core.thread.Consumer;
import ua.knu.csc.core.thread.Middleman;
import ua.knu.csc.core.thread.Producer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Storage storage1 = new Storage("[Ivanov - Petrov]");
        Storage storage2 = new Storage("[Petrov - Nechyporchuk]");

        // TODO: repair (?) the issue below.
        // The truck's capacity must be equal or more than the number of items in the warehouse (warehouse's size).
        final int NUMBER_OF_ITEMS_IN_WAREHOUSE = 70;
        final int TRUCK_CAPACITY = 100;

        Warehouse warehouse = new Warehouse(NUMBER_OF_ITEMS_IN_WAREHOUSE);
        Truck truck = new Truck(TRUCK_CAPACITY);

        Producer producer = new Producer("Ivanov", storage1, warehouse);
        Middleman middleman = new Middleman("Petrov", storage1, storage2);
        Consumer consumer = new Consumer("Nechyporchuk", storage2, truck);

        System.out.println("Number of items in the WAREHOUSE: " + warehouse.getSize());
        System.out.println("Number of items in the TRUCK: " + truck.getSize());
        System.out.println();

        producer.start();
        middleman.start();
        consumer.start();

        producer.join();
        middleman.join();
        consumer.join();

        System.out.println("Number of items in the TRUCK: " + truck.getSize() + ".");
    }
}
