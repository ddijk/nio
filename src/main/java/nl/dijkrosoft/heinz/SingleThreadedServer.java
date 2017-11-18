package nl.dijkrosoft.heinz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class SingleThreadedServer implements  SocketHandler {

    private String id;

    Consumer<Socket> runner = socket -> handle(socket);
    private final AbstractServer abstractServer;

    public SingleThreadedServer(int port, String id) {
        abstractServer = new AbstractServer(port, socket -> runner);
        this.id = id;
     }


    public static void main(String[] args) throws IOException {
        SingleThreadedServer singleThreadedServer = new SingleThreadedServer(9001, "SINGLE");
        singleThreadedServer.start();
    }

    void handle(Socket socket) {
        try {
            doit(socket.getInputStream(), socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void doit(InputStream inp, OutputStream out) {
        System.out.println(String.format("%s: We hebben een beller.", id));

        int i = 0;
        try {
            i = inp.read();
            while (i != -1) {
                out.write(Util.transmogrify(i));
                i = inp.read();
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        System.out.println(String.format("%s: Exiting with %d. ", id, i));
    }


    @Override
    public void start() throws IOException {

        abstractServer.start();
    }
}
