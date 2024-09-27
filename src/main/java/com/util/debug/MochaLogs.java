package com.util.debug;
import com.util.debug.basic.NativeWin;
import javax.swing.*;
/**
 * <p>Title: MochaLogs</p>
 * <p>Description: creates a Native Window that allows for debugging the engine or the
 * game being made with the engine.</p>
 */
public class MochaLogs extends NativeWin{
    private JTextArea textArea;
    private int numExceptions;
    public MochaLogs(){
        super("MochaLogs",640, 360);
        textArea = new JTextArea();
        textArea.setEditable(false);
        numExceptions = 0;
        JScrollPane scroller = new JScrollPane(textArea);
        this.getFrame().add(textArea);
    }

    public void log(String message){
        textArea.append(message + "\n");
    }

    /**
     * exceptionMessage method - displays a window of the exception thrown
     * @param ex
     */
    public void exceptionMessage(Exception ex){
        this.log(ex.getMessage());
        ++numExceptions;
    }

    /**
     * runtimeExceptionMessage
     * @param ex
     */
    public void runtimeExceptionMessage(RuntimeException ex){
        this.log(ex.getMessage());
        ++numExceptions;
    }

    /**
     * getNumExceptions accessor method - returns the amount of exception messages that were added to the window.
     * @return
     */
    public int getNumExceptions(){
        return numExceptions;
    }
}
