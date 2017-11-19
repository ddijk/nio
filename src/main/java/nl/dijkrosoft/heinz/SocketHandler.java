package nl.dijkrosoft.heinz;

import java.io.IOException;

public interface SocketHandler<S> {

    void handle(S s) throws IOException;
}
