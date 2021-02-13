package ua.knu.csc.core;

import javax.swing.JSlider;

public class WorkerThread extends Thread {
    private final long THREAD_DELAY = 200000000;

    private final BinarySemaphore binarySemaphore;

    private final JSlider slider;
    private final int targetSliderValue;

    public WorkerThread(BinarySemaphore binarySemaphore, JSlider slider, int targetSliderValue) {
        this.binarySemaphore = binarySemaphore;
        this.slider = slider;
        this.targetSliderValue = targetSliderValue;
    }

    public WorkerThread(String name, BinarySemaphore binarySemaphore, JSlider slider, int targetSliderValue) {
        super(name);
        this.binarySemaphore = binarySemaphore;
        this.slider = slider;
        this.targetSliderValue = targetSliderValue;
    }

    @Override
    public void run() {
        try {
            binarySemaphore.acquire();
        } catch (InterruptedException e) {
            System.err.println("[WorkerThread]: " + Thread.currentThread().getName() + " was interrupted before or while was waiting for a permit.");
            return;
        }

        int currentSliderValue;
        while (!isInterrupted()) {
            currentSliderValue = slider.getValue();

            if (currentSliderValue > targetSliderValue) {
                slider.setValue(currentSliderValue - 1);
            } else if (currentSliderValue < targetSliderValue) {
                slider.setValue(currentSliderValue + 1);
            } else {
                break;
            }

            long threadDelay = THREAD_DELAY;
            while (threadDelay != 0) threadDelay--;
        }

        binarySemaphore.release();
    }
}
