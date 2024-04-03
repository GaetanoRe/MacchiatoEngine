package org.app;

import org.util.*;
import org.util.debug.DebugThread;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Window win = new Window("MochaEngine", 1280, 720);
        DebugThread debug = new DebugThread();
        debug.run();
        if(!debug.isExceptionThrown()){
            win.runLoop();
            if(debug.isExceptionThrown()){
                win.stopLoop();
            }
        }
    }
}