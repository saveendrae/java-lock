package com.saveendrae.javalock;

import com.saveendrae.javalock.nonreentrant.NonReentrantLock;
import com.saveendrae.javalock.reentrant.ReentrantLock;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AppTest {

    private NonReentrantLock nonReentrantLock = new NonReentrantLock();
    private ReentrantLock reentrantLock = new ReentrantLock();

    @Test(timeout = 5000)
    public void Should_Fail_When_UseNonReentrantLock() {
        new Thread(() -> {
            try {
                this.outerNonReentrantLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(1000);
            this.outerNonReentrantLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    @Test(timeout = 10000)
    public void Should_Pass_When_UseReentrantLock() {
        new Thread(() -> {
            try {
                this.outerReentrantLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(1000);
            this.outerReentrantLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

    private synchronized void outerNonReentrantLock() throws InterruptedException {
        nonReentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + " NonReentrantLock Outer Start");
        innerNonReentrantLock();
        System.out.println(Thread.currentThread().getName() + " NonReentrantLock Outer End");
        nonReentrantLock.unlock();
    }

    private void innerNonReentrantLock() throws InterruptedException {
        nonReentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + " NonReentrantLock Inner Start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " NonReentrantLock Inner End");
        nonReentrantLock.unlock();
    }

    private synchronized void outerReentrantLock() throws InterruptedException {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + " ReentrantLock Outer Start");
        innerNonReentrantLock();
        System.out.println(Thread.currentThread().getName() + " ReentrantLock Outer End");
        reentrantLock.unlock();
    }

    private void innerReentrantLock() throws InterruptedException {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + " ReentrantLock Inner Start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " ReentrantLock Inner End");
        reentrantLock.unlock();
    }
}
