package bai3_prime;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrimeServiceImpl extends UnicastRemoteObject implements PrimeService {

    public PrimeServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean isPrime(int n) throws RemoteException {
        System.out.printf("[Server] Kiem tra so nguyen to cho n = %d%n", n);
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; (long) i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
