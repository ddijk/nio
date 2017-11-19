package nl.dijkrosoft.heinz;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.Socket;

public class SocketHandlerImpl implements SocketHandler<Socket> {

    @Override
    public void handle(Socket s) throws IOException {
        int i = 0;
        try (s; InputStream inp = s.getInputStream(); OutputStream out = s.getOutputStream();) {
            i = inp.read();
            while (i != -1) {
                out.write(Util.transmogrify(i));
                i = inp.read();
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }
}
