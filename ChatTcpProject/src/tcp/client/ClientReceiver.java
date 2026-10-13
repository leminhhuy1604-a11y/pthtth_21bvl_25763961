package tcp.client;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReceiver implements Runnable {
    private final BufferedReader in;

    public ClientReceiver(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String msg;
            while ((msg = in.readLine()) != null) {
                if (msg.equals("LEFT_ROOM") || msg.equals("BYE")) {
                    break;
                }
                System.out.println(msg);
            }
        } catch (IOException ignored) {}
    }
}