package com.saveendrae.lock;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Comment before execute NonReenterantLockDemo
        ReenterantLockDemo reenterantLockDemo = new ReenterantLockDemo();
        reenterantLockDemo.doDemo();

        // Uncomment to execute NonReenterantLockDemo
        //NonReenterantLockDemo nonReenterantLockDemo = new NonReenterantLockDemo();
        //nonReenterantLockDemo.doDemo();

    }
}
