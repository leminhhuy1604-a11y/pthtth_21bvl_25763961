package bai7_geometry;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GeometryServer {
    public static final int PORT = 1099;
    public static final String SERVICE_NAME = "GeometryService";

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

            GeometryService service = new GeometryServiceImpl();
            registry.rebind(SERVICE_NAME, service);

            System.out.println("[Server] GeometryServer da san sang (" + SERVICE_NAME + ")...");
        } catch (Exception e) {
            System.err.println("[Server] Loi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
