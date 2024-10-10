package com.util;


import org.joml.*;

import java.util.LinkedList;

public class MochaObject {
    private Matrix4f position;
    private String name;
    private LinkedList<MochaObject> children;

    public MochaObject(){
        position = new Matrix4f().ortho2D(0, 0, 0, 0);
    }


}
