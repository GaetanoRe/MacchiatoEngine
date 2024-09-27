package com.util.debug;

import javax.swing.*;

/**
 * <p>Title: NativeNotif Class</p>
 * <p>Description: Displays a pop up notification to allow for proper debugging.</p>
 */
public class MochaNotif {
    private JOptionPane jpane;

    public MochaNotif(){
        jpane = new JOptionPane();
    }

    public void show(String message, String title){
        JOptionPane.showMessageDialog(jpane, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
