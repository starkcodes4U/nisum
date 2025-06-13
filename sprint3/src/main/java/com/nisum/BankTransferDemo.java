package com.nisum;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private final int id;
    private int balance;
    private final Lock lock = new ReentrantLock();

    public Account(int id, int initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public int getBalance() {
        return balance;
    }

    public Lock getLock() {
        return lock;
    }
}

class TransferTask implements Runnable {
    private final Account from;
    private final Account to;
    private final int amount;

    public TransferTask(Account from, Account to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            transferMoney(from, to, amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void transferMoney(Account from, Account to, int amount) throws InterruptedException {
        // Lock ordering to prevent deadlock (lowest ID first)
        Account first = from.getId() < to.getId() ? from : to;
        Account second = from.getId() < to.getId() ? to : from;

        // Try locking both accounts
        synchronizedLocks(first.getLock(), second.getLock(), () -> {
            if (from.withdraw(amount)) {
                to.deposit(amount);
                System.out.println("Transferred " + amount + " from Account#" + from.getId() +
                        " to Account#" + to.getId());
            } else {
                System.out.println("Insufficient funds in Account#" + from.getId());
            }
        });
    }

    // Method to lock both accounts safely
    private void synchronizedLocks(Lock lock1, Lock lock2, Runnable action) {
        lock1.lock();
        try {
            lock2.lock();
            try {
                action.run(); // execute critical section
            } finally {
                lock2.unlock();
            }
        } finally {
            lock1.unlock();
        }
    }
}

public class BankTransferDemo {
    public static void main(String[] args) {
        Account acc1 = new Account(1, 1000);
        Account acc2 = new Account(2, 1000);
        Account acc3 = new Account(3, 1000);

        Thread t1 = new Thread(new TransferTask(acc1, acc2, 300));
        Thread t2 = new Thread(new TransferTask(acc2, acc1, 500));
        Thread t3 = new Thread(new TransferTask(acc2, acc3, 200));
        Thread t4 = new Thread(new TransferTask(acc3, acc1, 100));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

