package nl.dijkrosoft.heinz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public class SingleThreadedServer {
    public static void main(String[] args) throws IOException {

        char b = 'b';
        System.out.println(b);
        System.out.println("Hello World!");
        ServerSocket ss = new ServerSocket(9000);

        while (true) {
            try (Socket s = ss.accept(); InputStream inp = s.getInputStream(); OutputStream out = s.getOutputStream()) {

                System.out.println("We hebben een beller. "+ s.toString());


                int i = inp.read();
                while (i != -1) {
                    out.write(Util.transmogrify(i));
                    i = inp.read();
                }
                System.out.println("Exiting with " + i);
            } finally {
                System.out.printf("Bye..");
            }
           // System.exit(0);
        }
    }

}
