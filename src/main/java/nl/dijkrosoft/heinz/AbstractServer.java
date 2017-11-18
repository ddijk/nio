package nl.dijkrosoft.heinz;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.function.Function;

public class AbstractServer implements SocketHandler {


    private int port;
    private Function<Socket, Consumer<Socket>> runnerProvider;

    public AbstractServer(int port, Function<Socket, Consumer<Socket>> runnerProvider) {
        this.port = port;
        this.runnerProvider = runnerProvider;
    }

    public void start() throws IOException {
        try (ServerSocket ss = new ServerSocket(port)) {

            while (true) {
                Socket s = ss.accept();

                System.out.println("We hebben een beller. " + s.toString());

                Consumer<Socket> runner = runnerProvider.apply(s);
                runner.accept(s);
            }
        } finally {
            System.out.println("Going down...");
        }
    }


//        try (s) {
//            InputStream inp = s.getInputStream();
//            OutputStream out = s.getOutputStream();
//            int i = inp.read();
//            while (i != -1) {
//                out.write(Util.transmogrify(i));
//                i = inp.read();
//            }
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        } finally {
//            System.out.println("Closed " + s);
//        }


    //  }

}
