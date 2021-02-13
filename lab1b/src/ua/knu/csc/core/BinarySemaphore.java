package ua.knu.csc.core;

public class BinarySemaphore {
    private int permits = 1;

    public synchronized void acquire() throws InterruptedException {
        while (permits == 0) {
            System.out.println("[BinarySemaphore]: No permit is available. " + Thread.currentThread().getName() + " is waiting for the permit.");
            wait();
        }
        permits = 0;
        System.out.println("[BinarySemaphore]: " + Thread.currentThread().getName() + " acquires a permit.");
    }

    public synchronized void release() {
        permits = 1;
        System.out.println("[BinarySemaphore]: " + Thread.currentThread().getName() + " releases the permit.");
        notify();
    }

    public synchronized boolean tryAcquire() {
        if (permits == 0) {
            return false;
        }
        permits = 0;
        return true;
    }
}
