package org.util;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

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

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        GLFWVidMode vidMode = glfwGetVideoMode(window);

    }

    /**
     * getKey method - returns whether a specified key was pressed or released. MUST USE
     * GLFW NAMED KEYS, for Example: GLFW_KEY_0
     * @param key that was pressed
     * @return PRESSED or RELEASED
     */
    private int getKey(int key){
        return glfwGetKey(window, key);
    }

    /**
     * runLoop method - displays the window created, handles the input and manages the renderers utilized in
     * the engine.
     */
    public void runLoop(){
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        InputHandler inputHandler = new InputHandler(window);
        Renderer renderer = new Renderer();
        Texture texture = new Texture("GabeTheGhost.png");
        float x = 0;
        float y = 0;
        while(running){
            glfwPollEvents();
            if(glfwWindowShouldClose(window)){
                running = false;
            }
            // Input Handling here
            if(inputHandler.isButtonPressed(GLFW_KEY_UP)){
                y += 0.01f;
            }
            if(inputHandler.isButtonPressed(GLFW_KEY_LEFT)){
                x -= 0.01f;
            }
            if(inputHandler.isButtonPressed(GLFW_KEY_RIGHT)){
                x += 0.01f;
            }
            if(inputHandler.isButtonPressed(GLFW_KEY_DOWN)){
                y -= 0.01f;
            }
            // Rendering Handling here
            renderer.renderText(texture, x, y, 0.25f, 0.25f);
            glfwSwapBuffers(window);

        }

        glfwTerminate();
    }

}
