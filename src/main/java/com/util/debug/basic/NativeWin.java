package com.util.debug.basic;
import javax.swing.*;

/**
 * <p>Title: NativeWin Class</p>
 * <p>Description: Creates a window utilizing Java's built in swing and awt libraries.
 * This is used to try and not use up the resources provided by LWJGL and to help with Debugging.</p>
 *
 */
public class NativeWin {
    private JFrame frame;
    private String title;
    private int length, width;

    /**
     * parameterized constructor - allows for a basic window to be created.
     *
     * @param title
     * @param length
     * @param width
     */
    public NativeWin(String title, int length, int width) {
        this.title = title;
        this.length = length;
        this.width = width;
        frame = new JFrame(title);
        frame.setSize(length, width);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * getFrame accessor method - allows for access to the actual window.
     * @return
     */
    public JFrame getFrame() {
        return frame;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
