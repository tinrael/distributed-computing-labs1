package ua.knu.csc.core;

import javax.swing.JSlider;

public class Worker extends Thread {
    private final BinarySemaphore binarySemaphore;

    private final JSlider slider;

    public Worker(BinarySemaphore binarySemaphore, JSlider slider) {
        this.binarySemaphore = binarySemaphore;
        this.slider = slider;
    }

    public Worker(String name, BinarySemaphore binarySemaphore, JSlider slider) {
        super(name);
        this.binarySemaphore = binarySemaphore;
        this.slider = slider;
    }

    @Override
    public void run() {
        try {
            binarySemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            binarySemaphore.release();
        }
    }
}
