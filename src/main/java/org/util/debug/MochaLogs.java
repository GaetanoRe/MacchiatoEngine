package org.util.debug;
import org.util.debug.basic.NativeWin;
import javax.swing.*;
/**
 * <p>Title: MochaLogs</p>
 * <p>Description: creates a Native Window that allows for debugging the engine or the
 * game being made with the engine.</p>
 */
public class MochaLogs extends NativeWin{
    private JTextArea textArea;
    public MochaLogs(){
        super("MochaLogs",640, 360);
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(textArea);
        this.getFrame().add(textArea);
    }

    public void log(String message){
        textArea.append(message + "\n");
    }
}
