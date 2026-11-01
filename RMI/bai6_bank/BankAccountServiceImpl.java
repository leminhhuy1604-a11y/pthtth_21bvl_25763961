package bai6_bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankAccountServiceImpl extends UnicastRemoteObject implements BankAccountService {

    private double balance;

    public BankAccountServiceImpl(double initialBalance) throws RemoteException {
        super();
        this.balance = initialBalance;
    }

    @Override
    public synchronized double getBalance() throws RemoteException {
        System.out.printf("[Server] Kiem tra so du: %.2f VND%n", balance);
        return balance;
    }

    @Override
    public synchronized void deposit(double amount) throws RemoteException {
        if (amount <= 0) {
            System.out.printf("[Server] Nap tien that bai: so tien phai lon hon 0 (%.2f)%n", amount);
            throw new IllegalArgumentException("So tien gui phai lon hon 0!");
        }
        balance += amount;
        System.out.printf("[Server] Nap thanh cong: +%.2f VND. So du moi: %.2f VND%n", amount, balance);
    }

    @Override
    public synchronized boolean withdraw(double amount) throws RemoteException {
        if (amount <= 0) {
            System.out.printf("[Server] Rut tien that bai: so tien phai lon hon 0 (%.2f)%n", amount);
            return false;
        }
        if (amount > balance) {
            System.out.printf("[Server] Rut tien that bai: So du khong du (Yeu cau: %.2f, So du hien co: %.2f)%n", amount, balance);
            return false;
        }
        balance -= amount;
        System.out.printf("[Server] Rut thanh cong: -%.2f VND. So du moi: %.2f VND%n", amount, balance);
        return true;
    }
}
