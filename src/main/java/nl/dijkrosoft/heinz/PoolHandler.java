package nl.dijkrosoft.heinz;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class PoolHandler implements SocketHandler<Socket> {

    private final SocketHandler<Socket> other;
    private final ExecutorService executorService;
    private final Thread.UncaughtExceptionHandler exceptionHandler;

    public PoolHandler(SocketHandler<Socket> other, ExecutorService executorService, Thread.UncaughtExceptionHandler exceptionHandler) {
        this.other = other;
        this.executorService = executorService;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void handle(Socket socket) throws IOException {


        executorService.submit(new FutureTask<>(()-> { other.handle(socket); return null;}) {
            @Override
            protected void setException(Throwable t) {
                exceptionHandler.uncaughtException(Thread.currentThread(), t);
            }
        } );
    }
}
