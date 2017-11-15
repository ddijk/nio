package heinz.dijkrosoft.nl;
import java.io.Console;

public class MyConsole {

    public static void main(String[] args) {

        Console console = System.console();

        if (console == null) {
            System.out.println("Geen console, exiting");
            System.exit(0);
        }

        String line;
        do {
            line = console.readLine();
            System.out.println(line);

        } while (!line.trim().isEmpty());
    }
}
