package com.util;


import org.joml.*;

import com.util.Window;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.LinkedList;

public class Object {
    private Matrix4f position;
    private String name;
    private LinkedList<Object> children;

    public Object(){
        position = new Matrix4f().ortho2D(0, 0, 0, 0);
    }


}
