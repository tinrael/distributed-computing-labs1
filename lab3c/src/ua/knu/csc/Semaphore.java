package ua.knu.csc;

public class Semaphore {
    private int permits;

    public Semaphore(int permits) {
        this.permits = permits;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits == 0) {
            System.out.println("[Semaphore]: No permit is available. " + Thread.currentThread().getName() + " is waiting for the permit.");
            wait();
        }
        permits--;
        System.out.println("[Semaphore]: " + Thread.currentThread().getName() + " acquires a permit.");
    }

    public synchronized void release() {
        permits++;
        System.out.println("[Semaphore]: " + Thread.currentThread().getName() + " releases the permit.");
        notify();
    }

    public synchronized boolean tryAcquire() {
        if (permits == 0) {
            return false;
        }
        permits--;
        return true;
    }
}
