package ua.knu.csc.core;

public class BinarySemaphore {
    private final JLabelWrapper statusBarText;

    private int permits = 1;

    public BinarySemaphore(JLabelWrapper statusBarText) {
        if (statusBarText == null) {
            throw new IllegalArgumentException("The null argument 'statusBarText' is not allowed.");
        }

        this.statusBarText = statusBarText;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits == 0) {
            statusBarText.setText("No permit is available. " + Thread.currentThread().getName() + " is waiting for the permit.");
            System.out.println("[BinarySemaphore]: No permit is available. " + Thread.currentThread().getName() + " is waiting for the permit.");

            wait();
        }
        permits = 0;

        statusBarText.setText(Thread.currentThread().getName() + " acquires a permit.");
        System.out.println("[BinarySemaphore]: " + Thread.currentThread().getName() + " acquires a permit.");
    }

    public synchronized void release() {
        permits = 1;

        statusBarText.setText(Thread.currentThread().getName() + " releases the permit.");
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
