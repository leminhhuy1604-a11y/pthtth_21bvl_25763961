package exercises;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class Exercise01_HostAndUriInspector {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java exercises.Exercise01_HostAndUriInspector <hostname> <uri>");
            return;
        }

        String host = args[0];
        String uriStr = args[1];

        // 1. Khảo sát Host
        System.out.println("=== HOST INSPECTION ===");
        try {
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress address : addresses) {
                System.out.println("IP: " + address.getHostAddress() 
                    + " | IPv4/IPv6: " + (address instanceof java.net.Inet4Address ? "IPv4" : "IPv6")
                    + " | Loopback: " + address.isLoopbackAddress()
                    + " | Site local: " + address.isSiteLocalAddress());
            }
        } catch (UnknownHostException e) {
            System.err.println("Không phân giải được host: " + host);
        }

        // 2. Khảo sát URI
        System.out.println("\n=== URI INSPECTION ===");
        try {
            URI uri = new URI(uriStr);
            System.out.println("Scheme: " + uri.getScheme());
            System.out.println("Host: " + uri.getHost());
            System.out.println("Port: " + uri.getPort());
            System.out.println("Path: " + uri.getPath());
            System.out.println("Query: " + uri.getQuery());
            System.out.println("Fragment: " + uri.getFragment());
        } catch (Exception e) {
            System.err.println("URI không hợp lệ: " + e.getMessage());
        }
    }
}