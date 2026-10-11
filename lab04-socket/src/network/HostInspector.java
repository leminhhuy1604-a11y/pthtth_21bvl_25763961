package network;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostInspector {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java network.HostInspector <hostname>");
            return;
        }

        String host = args[0];
        try {
            InetAddress[] addresses = InetAddress.getAllByName(host);
            System.out.println("Host: " + host);
            for (InetAddress address : addresses) {
                System.out.println("- IP: " + address.getHostAddress());
                System.out.println("  Canonical: " + address.getCanonicalHostName());
                System.out.println("  Loopback: " + address.isLoopbackAddress());
                System.out.println("  Site local: " + address.isSiteLocalAddress());
                
                // Bổ sung kiểm tra IPv4 hay IPv6
                if (address instanceof Inet4Address) {
                    System.out.println("  Type: IPv4");
                } else if (address instanceof Inet6Address) {
                    System.out.println("  Type: IPv6");
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Không phân giải được host: " + host);
        }
    }
}