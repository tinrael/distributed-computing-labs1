package ua.knu.csc.core;

import ua.knu.csc.ui.MainWindow;

public class ThreadsRunner {
    private final long THREAD_DELAY = 2000000;

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
                while (true) {
                    if (mainWindow.getSliderValue() > 10) {
                        mainWindow.decreaseSliderValueByOne();

                        long threadDelay = THREAD_DELAY;
                        while (threadDelay != 0) threadDelay--;
                    }
                }
            }
        });
        thread1.setName("Thread-1");
        thread1.setDaemon(true);

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if (mainWindow.getSliderValue() < 90) {
                        mainWindow.increaseSliderValueByOne();

                        long threadDelay = THREAD_DELAY;
                        while (threadDelay != 0) threadDelay--;
                    }
                }
            }
        });
        thread2.setName("Thread-2");
        thread2.setDaemon(true);
    }

    public void startThreads() {
        thread1.start();
        thread2.start();
    }

    public void setPriorityThread1(int newPriority) {
        System.out.println("[ThreadsRunner]: Set " + thread1.getName() + " priority to " + newPriority + ".");
        thread1.setPriority(newPriority);
    }

    public void setPriorityThread2(int newPriority) {
        System.out.println("[ThreadsRunner]: Set " + thread2.getName() + " priority to " + newPriority + ".");
        thread2.setPriority(newPriority);
    }
}
