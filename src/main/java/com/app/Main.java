package com.app;

import com.util.Window;
import com.util.*;
import com.util.debug.Camera2D;
import com.util.debug.MochaLogs;
import com.util.debug.Shader;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_NO_ERROR;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MochaLogs mLogs = new MochaLogs();
        Window win = new Window("MochaEngine", 1280, 720);
        
        MochaInterpreter interpreter = new MochaInterpreter("window");
        //MochaInterpreter inputInt = new MochaInterpreter("input");
        try {
			interpreter.Interpret(win);
            //inputInt.Interpret(win);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        mLogs.log("Game Running");
        String glErr = gameLoop(win);
        mLogs.log(glErr);
    }

    /**
     * gameLoop method - displays the window created, handles the input and manages the renderers utilized in
     * the engine.
     */
    public static String gameLoop(Window win){
        win.showWindow();
        int glError = 0;
        //MochaInputHandler inputHandler = iH;
        Camera2D camera = new Camera2D(win.getWidth(), win.getHeight());
        float [] verticies = new float[] {
                -1f, 1f, 0, // TOP LEFT
                1f, 1f, 0, // TOP RIGHT
                1f, -1f, 0, // BOTTOM RIGHT
                -1f, -1f, 0, // BOTTOM LEFT
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
//        Matrix4f projection = new Matrix4f().ortho2D((float) -this.width /2, (float) this.width /2, (float) -this.height /2,
//                (float) this.height /2);
        Matrix4f scale = new Matrix4f().scale(50);
        Matrix4f translate = new Matrix4f().translate(0, 0, 0);
        Matrix4f target = new Matrix4f();

//        projection.mul(scale, target);

        target.mul(translate, target);
        //Texture texture2 = new Texture("src/main/resources/textures/Smiley.png", 1);

        Shader shader = new Shader("shader");

        camera.setPosition(new Vector3f(0, 0, 0));

        // rq.addRenderItem(texture,new float[] {2, 2}, new float [] {0f, 0f}, textModel);
        // rq.addRenderItem(texture2,new float[] {2, 2}, new float [] {0, 0f}, textModel);

        String glErrorMessage = "";
        //float x = rq.getPositionAtZ(0)[0];
        //float y = rq.getPositionAtZ(0)[1];

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        while(win.isRunning()){
            target = scale;
            glfwPollEvents();
            if(win.closed()){
                win.setRunning(false);
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
            shader.setUniform("projection", camera.getProjection().mul(target));
            texture.bind(0);
            textModel.render();

            glError = glGetError();
            if(glError != GL_NO_ERROR){
                glErrorMessage += "OpenGL Error: " + glError + "\n";
            }
            win.swapBuffers();

        }
        stopLoop();
        return glErrorMessage;
    }

    public static void stopLoop(){
        glDisable(GL_BLEND);
        glfwTerminate();
    }
}