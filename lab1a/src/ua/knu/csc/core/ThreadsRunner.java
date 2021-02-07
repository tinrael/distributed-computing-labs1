package ua.knu.csc.core;

import ua.knu.csc.ui.MainWindow;

public class ThreadsRunner {
    private final long THREAD_DELAY = 1500000;

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
                    mainWindow.increaseSliderValueByOne();
                    
                    long threadDelay = THREAD_DELAY;
                    while (threadDelay != 0) threadDelay--;
                }
            }
        });
        thread1.setName("Thread-1");

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    mainWindow.decreaseSliderValueByOne();

                    long threadDelay = THREAD_DELAY;
                    while (threadDelay != 0) threadDelay--;
                }
            }
        });
        thread2.setName("Thread-2");
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
