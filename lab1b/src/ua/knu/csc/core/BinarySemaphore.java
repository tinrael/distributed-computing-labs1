package ua.knu.csc.core;

public class BinarySemaphore {
    private int permits = 1;

    public synchronized void acquire() throws InterruptedException {
        while (permits == 0) {
            wait();
        }
        permits = 0;
    }

    public synchronized void release() {
        permits = 1;
        notify();
    }
}
