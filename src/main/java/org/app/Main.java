package org.app;

import org.util.*;
import org.util.debug.DebugThread;
import org.util.debug.MochaLogs;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MochaLogs mLogs = new MochaLogs();
        Window win = new Window("MochaEngine", 1280, 720);
        String glErr = win.runLoop();
        mLogs.log(glErr);
    }
}