package nl.dijkrosoft.heinz;

import java.io.IOException;

public class PrintHandler<S> implements SocketHandler<S> {


    private SocketHandler<S> h;

    public PrintHandler(SocketHandler<S> h) {
        this.h = h;
    }

    @Override
    public void handle(S s) throws IOException {

        System.out.println("Before handling");

        h.handle(s);
        System.out.println("After handling");

    }
}
