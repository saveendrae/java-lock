package com.saveendrae.lock;

import com.saveendrae.lock.nonreentrant.NonReentrantLock;

public class NonReenterantLockDemo {

    NonReentrantLock lock = new NonReentrantLock();

    public void doDemo() {
        new Thread(() -> {
            try {
                this.outer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                this.outer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                this.outer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public synchronized void outer() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " lock outer start");
        inner();
        System.out.println(Thread.currentThread().getName() + " lock outer end");
        lock.unlock();
    }

    public void inner() throws InterruptedException {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " lock inner start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " lock inner end");
        lock.unlock();
    }
}
