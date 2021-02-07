package ua.knu.csc.core;

import ua.knu.csc.ui.MainWindow;

public class ThreadsRunner {
    private final MainWindow mainWindow;

    private Thread thread1;
    private Thread thread2;

    public ThreadsRunner(MainWindow mainWindow, int priorityThread1, int priorityThread2) {
        this.mainWindow = mainWindow;

        createThreads();

        setPriorityThread1(priorityThread1);
        setPriorityThread2(priorityThread2);
    }

    private void createThreads() {
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public void startThreads() {
        thread1.start();
        thread2.start();
    }

    public void setPriorityThread1(int newPriority) {
        thread1.setPriority(newPriority);
    }

    public void setPriorityThread2(int newPriority) {
        thread2.setPriority(newPriority);
    }
}
