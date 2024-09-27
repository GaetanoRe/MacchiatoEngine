package com.app;

import com.util.Window;
import com.util.*;
import com.util.debug.MochaLogs;

public class Main {
    public static void main(String[] args) {
        MochaLogs mLogs = new MochaLogs();
        Window win = new Window("MochaEngine", 1280, 720);
        String glErr = win.runLoop();
        mLogs.log(glErr);
    }
}