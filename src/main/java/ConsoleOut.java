import javax.swing.*;
import java.io.OutputStream;

public class ConsoleOut extends OutputStream {

    private final JTextArea textArea;

    /**
     * Creates a new ConsoleOut, which is an extension of OutputStream.
     * The only difference is an override on the write(int) method.
     * @param textArea The textArea to apply this change to.
     */
    public ConsoleOut(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {

        // redirects data to the text area
        textArea.append(String.valueOf((char)b));

        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}