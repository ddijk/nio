package nl.dijkrosoft.heinz;

import java.io.IOException;
import java.net.Socket;

public class NastyChump {
    public static void main(String[] args) throws InterruptedException {
        Socket[] clients = new Socket[3000];

        for (int i=0; i<clients.length;i++) {
            try {
                clients[i] = new Socket("localhost", 9000);
                System.out.println("Connected " + i);
            } catch (IOException e) {
                System.err.println("Failed to create connection for "+ i);
            }
        }

        Thread.sleep(100_000);
    }
}
