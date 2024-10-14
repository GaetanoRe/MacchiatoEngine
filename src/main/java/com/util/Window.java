package com.util;
import com.util.debug.Camera2D;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import com.util.debug.Shader;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * <p>Title: Window Class</p>
 * <p>Description: This is the application that deals with the opening of the window. It also
 * utilizes the Renderer class to render textures onto it and processes input to be handled by other
 * classes. It is essentially the 'window' between the User's Input and the game itself.</p>
 */
public class Window {

    // Dictates whether the window is running or not.
    private boolean running;

    // The long value representation created by GLFW. Allows for other classes to access the window.
    private long window;

    // The window's width
    private int width;

    // The window's height
    private int height;

    // The name displayed on the Window
    private String winName;

//    private MochaInputHandler inputManager;

    /**
     * Default constructor - Instantiates the window. Doesn't show the window yet.
     * @param winName
     * @param width
     * @param height
     */
    public Window(String winName, int width, int height){
        if(!glfwInit()){
            throw new IllegalStateException("The window failed to be created");
        }
        this.running = true;
        this.width = width;
        this.height = height;
        this.winName = winName;
        window = glfwCreateWindow(this.width, this.height, this.winName, 0, 0);
        if(window == 0){
            throw new IllegalStateException("Window failed to be created though GLFW was initialized.");
        }
       // this.inputManager = new MochaInputHandler(this.window);

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        GLFWVidMode vidMode = glfwGetVideoMode(window);

    }

    public boolean closed(){
        return glfwWindowShouldClose(window);
    }

    public long getWindow(){
        return window;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight(){
        return height;
    }

    public boolean isRunning(){
        return running;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    /**
     * getKey method - returns whether a specified key was pressed or released. MUST USE
     * GLFW NAMED KEYS, for Example: GLFW_KEY_0
     * @param key that was pressed
     * @return PRESSED or RELEASED
     */
    public int getKey(int key){
        return glfwGetKey(window, key);
    }

    public void resizeWindow(int width, int height){
        this.width = width;
        this.height = height;
        glfwSetWindowSize(this.window, this.width, this.height);
    }

//    public void setInputManager(MochaInputHandler mi){
//        this.inputManager = mi;
//    }
//
//    public MochaInputHandler getInputManager(){
//        return this.inputManager;
//    }

    public void showWindow(){
        glfwShowWindow(window);
    }

    public void destroyWindow(){
        glfwDestroyWindow(window);
    }

    public void swapBuffers(){
        glfwSwapBuffers(window);
    }


}
