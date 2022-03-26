import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOut extends OutputStream {

    private JTextArea textArea;

    public ConsoleOut(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
        // keeps the textArea up to date
        //textArea.update(textArea.getGraphics());
    }
}