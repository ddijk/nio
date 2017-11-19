package nl.dijkrosoft.heinz;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.Socket;

public class MultiThreadedHandler implements SocketHandler<Socket> {
    private SocketHandler<Socket> socketHandler;

    public MultiThreadedHandler(SocketHandler<Socket> socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public void handle(Socket socket) throws IOException {

        new Thread(() -> {
            try {
                socketHandler.handle(socket);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }).start();

    }
}
