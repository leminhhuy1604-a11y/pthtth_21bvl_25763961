package bai7_geometry;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GeometryServiceImpl extends UnicastRemoteObject implements GeometryService {

    public GeometryServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public double rectangleArea(double width, double height) throws RemoteException {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Kich thuoc hinh chu nhat khong duoc am!");
        }
        double area = width * height;
        System.out.printf("[Server] Tinh dien tich hinh chu nhat (w=%.2f, h=%.2f) = %.4f%n", width, height, area);
        return area;
    }

    @Override
    public double circleArea(double radius) throws RemoteException {
        if (radius < 0) {
            throw new IllegalArgumentException("Ban kinh hinh tron khong duoc am!");
        }
        double area = Math.PI * radius * radius;
        System.out.printf("[Server] Tinh dien tich hinh tron (r=%.2f) = %.4f%n", radius, area);
        return area;
    }

    @Override
    public double triangleArea(double base, double height) throws RemoteException {
        if (base < 0 || height < 0) {
            throw new IllegalArgumentException("Day va chieu cao tam giac khong duoc am!");
        }
        double area = 0.5 * base * height;
        System.out.printf("[Server] Tinh dien tich hinh tam giac (base=%.2f, h=%.2f) = %.4f%n", base, height, area);
        return area;
    }
}
