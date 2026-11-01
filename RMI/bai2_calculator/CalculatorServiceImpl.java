package bai2_calculator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServiceImpl extends UnicastRemoteObject implements CalculatorService {

    public CalculatorServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        int sum = a + b;
        System.out.printf("[Server] Nhan yeu cau tinh tong: %d + %d = %d%n", a, b, sum);
        return sum;
    }
}
