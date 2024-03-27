package org.util;
import static org.lwjgl.glfw.GLFW.*;
import java.util.*;
public class InputHandler {
    private class Command{
        private String command;
        private int key;

        public Command(String command, int key){
            this.command = command;
            this.key = key;
        }

        public boolean equals(String command){
            return this.command.equals(command);
        }

        public int getKey(){
            return key;
        }

        public String getCommand(){
            return command;
        }
    }
    private long window;
    private LinkedList<Command> commands;

    public InputHandler(long window){
        this.window = window;
        commands = new LinkedList<Command>();
    }

    public boolean isButtonPressed(int key){
        return glfwGetKey(window, key) == GLFW_PRESS;
    }

    public boolean isButtonReleased(int key){
        return glfwGetKey(window, key) == GLFW_RELEASE;
    }

    public void addCommand(String command, int key){
        commands.add(new Command(command, key));
    }

    public int getKeyBind(String command) {
        int keyFound = 0;
        for (Command c : commands){
            if(c.equals(command)){
                return c.getKey();
            }
        }
        return -1;
    }



}


