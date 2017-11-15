package heinz.dijkrosoft.nl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {

        char b = 'b';
        System.out.println(b);
        System.out.println( "Hello World!" );

        ServerSocket ss =  new ServerSocket(9000);

     //   Thread.currentThread().setDaemon(true);


        while ( true) {
            Socket s = ss.accept();

//            s.getInputStream().transferTo(s.getOutputStream());

           int i = s.getInputStream().read();
           while ( i != 0 && i != 65) {
               if ( Character.isAlphabetic(i)) {
                   char a = Character.forDigit(i ^ ' ',10);
                   System.out.println(a);
                   System.out.println(a);
               } else {
    
                   System.out.println("input is not alphabetic");
               }
               i = s.getInputStream().read();
           }
            System.out.println("Exiting with "+i);
           System.exit(0);
        }
    }
}
