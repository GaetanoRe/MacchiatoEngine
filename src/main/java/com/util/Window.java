package com.util;
import org.joml.Matrix4f;
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
        glfwMakeContextCurrent(window);

        GL.createCapabilities();

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
    public String runLoop(){
        glfwShowWindow(window);
        int glError = 0;
        MochaInputHandler inputHandler = new MochaInputHandler(window);
        float [] verticies = new float[] {
                -0.05f, 0.05f, 0, // TOP LEFT
                0.05f, 0.05f, 0, // TOP RIGHT
                0.05f, -0.05f, 0, // BOTTOM RIGHT
                -0.05f, -0.05f, 0, // BOTTOM LEFT
        };
        float [] textureCoords = new float []{
                0, 0,  // top left
                1, 0,  // top right
                1, 1,  // bottom right
                0, 1,  // bottom left

        };

        int [] indicies = new int []{
                0,1,2,
                2,3,0
        };
        MochaTextureHandler textModel = new MochaTextureHandler(verticies, textureCoords, indicies);
        Texture texture = new Texture("./resources/textures/GabeTheGhost.png", 0);
        Matrix4f projection = new Matrix4f().ortho2D((float) -this.width /2, (float) this.width /2, (float) -this.height /2,
                (float) this.height /2);
        Matrix4f scale = new Matrix4f().scale(1024);
        Matrix4f translate = new Matrix4f().translate(0, 0, 0);
        Matrix4f target = new Matrix4f();

        projection.mul(scale, target);
        target.mul(translate, target);
        //Texture texture2 = new Texture("src/main/resources/textures/Smiley.png", 1);

        Shader shader = new Shader("shader");

        RenderQueue rq = new RenderQueue();

       // rq.addRenderItem(texture,new float[] {2, 2}, new float [] {0f, 0f}, textModel);
       // rq.addRenderItem(texture2,new float[] {2, 2}, new float [] {0, 0f}, textModel);

        String glErrorMessage = "";
        //float x = rq.getPositionAtZ(0)[0];
        //float y = rq.getPositionAtZ(0)[1];

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        while(running){
            glfwPollEvents();
            if(glfwWindowShouldClose(window)){
                running = false;
            }
            // Input Handling here

            // Below is code that was used to move the quad with the previous rendering method.
//            if(inputHandler.isButtonPressed(GLFW_KEY_UP)){
//                y += 1f;
//            }
//            if(inputHandler.isButtonPressed(GLFW_KEY_LEFT)){
//                x -= 1f;
//            }
//            if(inputHandler.isButtonPressed(GLFW_KEY_RIGHT)){
//                x += 1f;
//            }
//            if(inputHandler.isButtonPressed(GLFW_KEY_DOWN)){
//                y -= 1f;
//            }

            //rq.setPositionAtZ(0, new float[] {x, y});
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // handle rendering of objects here.
            //rq.renderAll();
            shader.bind();
//            shader.setUniform("red", 1);
//            shader.setUniform("green", 0);
//            shader.setUniform("blue", 0);
//            shader.setUniform("alpha", 1);
            shader.setUniform("sampler", 0);
            shader.setUniform("projection", target);
            texture.bind(0);
            textModel.render();

            glError = glGetError();
            if(glError != GL_NO_ERROR){
                glErrorMessage += "OpenGL Error: " + glError + "\n";
            }
            glfwSwapBuffers(window);

        }
        glDisable(GL_BLEND);
        glfwTerminate();
        return glErrorMessage;
    }

    public void stopLoop(){
        glDisable(GL_BLEND);
        glfwTerminate();
    }

}
