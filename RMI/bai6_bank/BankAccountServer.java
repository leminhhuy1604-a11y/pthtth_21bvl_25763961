package bai6_bank;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BankAccountServer {
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "BankAccountService";
    public static final double INITIAL_BALANCE = 5000000.0; // 5 trieu VND ban dau

    public static void main(String[] args) {
        try {
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(PORT);
                System.out.println("[Server] RMI Registry da duoc khoi tao tai cong " + PORT);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(PORT);
                System.out.println("[Server] Su dung RMI Registry hien co tai cong " + PORT);
            }

            BankAccountService service = new BankAccountServiceImpl(INITIAL_BALANCE);
            registry.rebind(SERVICE_NAME, service);

            System.out.printf("[Server] BankAccountServer da san sang voi so du ban dau: %,.2f VND (%s)...%n",
                    INITIAL_BALANCE, SERVICE_NAME);
        } catch (Exception e) {
            System.err.println("[Server] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
