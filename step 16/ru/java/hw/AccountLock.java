package ru.java.hw;

import java.util.concurrent.locks.ReentrantLock;

public class AccountLock {
    private final ReentrantLock lock = new ReentrantLock();
    private double balance;
    private final int id;
    
    public AccountLock(int id) { 
        this.id = id; 
        this.balance = 0; 
    }
    
    public AccountLock(int id, double ib) { 
        this.id = id; 
        this.balance = ib; 
    }
    
    public void transfer(AccountLock to, double amount) {
        AccountLock accFirst = this.id < to.id ? this : to;
        AccountLock accSecond = this.id < to.id ? to : this;
        
        accFirst.lock.lock();
        try {
            accSecond.lock.lock();
            try {
                if (balance >= amount) {
                    balance -= amount;
                    to.balance += amount;
                }
            } finally {
                accSecond.lock.unlock();
            }
        } finally {
            accFirst.lock.unlock();
        }
    }
}