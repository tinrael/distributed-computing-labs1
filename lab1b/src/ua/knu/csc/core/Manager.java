package ua.knu.csc.core;

import javax.swing.*;

public class Manager {
    private final BinarySemaphore binarySemaphore = new BinarySemaphore();

    private WorkerThread workerThread1;
    private WorkerThread workerThread2;

    private final JSlider slider;

    private final JButton buttonStart1;
    private final JButton buttonStop1;

    private final JButton buttonStart2;
    private final JButton buttonStop2;

    public Manager(JSlider slider, JButton buttonStart1, JButton buttonStop1, JButton buttonStart2, JButton buttonStop2) {
        if (slider == null || buttonStart1 == null || buttonStop1 == null || buttonStart2 == null || buttonStop2 == null) {
            throw new IllegalArgumentException("The null arguments are not allowed.");
        }

        this.slider = slider;

        this.buttonStart1 = buttonStart1;
        this.buttonStop1 = buttonStop1;

        this.buttonStart2 = buttonStart2;
        this.buttonStop2 = buttonStop2;

        addActionListenersToButtons();
    }

    public void addActionListenersToButtons() {
        buttonStart1.addActionListener(e -> onButtonStart1Click());
        buttonStop1.addActionListener(e -> onButtonStop1Click());

        buttonStart2.addActionListener(e -> onButtonStart2Click());
        buttonStop2.addActionListener(e -> onButtonStop2Click());
    }

    private void onButtonStart1Click() {
        if (workerThread1 == null || !workerThread1.isAlive()) {
            workerThread1 = new WorkerThread(binarySemaphore, slider, 10);
            workerThread1.setPriority(Thread.MIN_PRIORITY);
            workerThread1.start();
        }
    }

    private void onButtonStop1Click() {
        if (workerThread1 != null && workerThread1.isAlive()) {
            workerThread1.interrupt();
        }
    }

    private void onButtonStart2Click() {
        if (workerThread2 == null || !workerThread2.isAlive()) {
            workerThread2 = new WorkerThread(binarySemaphore, slider, 90);
            workerThread2.setPriority(Thread.MAX_PRIORITY);
            workerThread2.start();
        }
    }

    private void onButtonStop2Click() {
        if (workerThread2 != null && workerThread2.isAlive()) {
            workerThread2.interrupt();
        }
    }
}
