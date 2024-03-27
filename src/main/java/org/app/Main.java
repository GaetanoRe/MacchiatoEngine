package org.app;

import org.util.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Window win = new Window("MochaEngine", 1280, 720);
        win.runLoop();
    }
}