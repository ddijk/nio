package nl.dijkrosoft.heinz;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolServer {
    public static void main(String[] args) throws IOException {


        ExecutorService executorService = Executors.newFixedThreadPool(10);

        SocketHandler<Socket> handler = new PoolHandler(new PrintHandler<>(new SocketHandlerImpl()), executorService,   (t,e) -> System.out.println("oops. " +e));

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
