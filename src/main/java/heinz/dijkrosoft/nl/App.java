package heinz.dijkrosoft.nl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

        char b = 'b';
        System.out.println(b);
        System.out.println("Hello World!");

        while (true) {
            try (ServerSocket ss = new ServerSocket(9000); Socket s = ss.accept(); InputStream inp = s.getInputStream(); OutputStream out = s.getOutputStream()) {

                int i = inp.read();
                while (i != -1) {
                    out.write(transmogrify(i));
                    i = inp.read();
                }
                System.out.println("Exiting with " + i);
            } finally {
                System.out.printf("Bye..");
            }
            System.exit(0);
        }
    }

    private static int transmogrify(final int i) {
        if (Character.isLetter(i)) {
            int x = i ^ ' ';
            System.out.println(x);
            return x;
        } else {

            System.out.println("input is not alphabetic: " + i);
            return i;
        }
    }
}
