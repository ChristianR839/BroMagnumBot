import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

public class ConsoleOutputCapturer {

    public ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public void setConsoleOut() {
        PrintStream o = new PrintStream(baos);

        // Store current System.out before assigning a new value
        PrintStream console = System.out;

        // Assign o to output stream
        System.setOut(o);
        System.out.println("This will be written to the text file");

        // Use stored value for output stream
        System.setOut(console);
        System.out.println("This will be written on the console!");
    }
}

// TODO: Get the console output to appear in window

// https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/