package nl.dijkrosoft.heinz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

public class AbstractServer implements  SocketHandler{


    private Consumer<Socket> handler;

    public AbstractServer(Consumer<Socket> handler) {
        this.handler = handler;
    }

    public void start() throws IOException {
        try (ServerSocket ss = new ServerSocket(9000)) {

            while (true) {
                Socket s = ss.accept();

                System.out.println("We hebben een beller. " + s.toString());

                        handler.accept(s);
            }
        } finally {
            System.out.println("Going down...");
        }
    }


    public void handleSocket(final Socket s) {

        try (s) {
            InputStream inp = s.getInputStream();
            OutputStream out = s.getOutputStream();
            int i = inp.read();
            while (i != -1) {
                out.write(Util.transmogrify(i));
                i = inp.read();
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } finally {
            System.out.println("Closed " + s);
        }


    }

}
