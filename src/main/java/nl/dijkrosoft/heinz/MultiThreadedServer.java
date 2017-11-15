package nl.dijkrosoft.heinz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {

        System.out.println(MultiThreadedServer.class.getName());
        System.out.println("Hello World!");
        try (ServerSocket ss = new ServerSocket(9000)) {

            while (true) {
                Socket s = ss.accept();

                System.out.println("We hebben een beller. " + s.toString());

                new Thread(() -> handleSocket(s)).start();
            }
        } finally {
            System.out.println("Going down...");
        }
    }

    private static void handleSocket(final Socket s) {

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
