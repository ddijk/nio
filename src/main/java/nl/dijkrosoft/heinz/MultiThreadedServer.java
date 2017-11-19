package nl.dijkrosoft.heinz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class MultiThreadedServer {

    public static void main(String[] args) throws IOException {

        SocketHandler<Socket> handler = new MultiThreadedHandler(new SocketHandlerImpl());

        try (ServerSocket ss = new ServerSocket(9000)) {

            while (true) {
                Socket s = ss.accept();

                handler.handle(s);


            }
        } finally {
            System.out.println("Going down...");
        }
    }
}
