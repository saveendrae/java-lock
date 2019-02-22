package com.saveendrae.javalock.reentrant;

public class ReentrantLock {

    private boolean isLocked = false;
    private Thread lockedBy;

    public synchronized void lock() throws InterruptedException {
        while (isLocked && Thread.currentThread() != lockedBy) {
            wait();
        }
        isLocked = true;
        lockedBy = Thread.currentThread();
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == lockedBy) {
            isLocked = false;
            lockedBy = null;
            notify();
        }
    }
}
