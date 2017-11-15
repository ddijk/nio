package nl.dijkrosoft.heinz;

import javax.naming.ldap.SortKey;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * Hello world!
 */
public class MultiThreadedServer implements SocketHandler {

    SocketHandler socketHandler;

    public static void main(String[] args) throws IOException {

        MultiThreadedServer multiThreadedServer = new MultiThreadedServer()
    }

    public MultiThreadedServer(SocketHandler socketHandler) {

        this.socketHandler = socketHandler;
        Consumer<Socket> handler = socket -> new Thread(() -> handleSocket(socket)).start();

    }

    @Override
    public void handleSocket(Socket s) {
        socketHandler.handleSocket(s);
    }
}
