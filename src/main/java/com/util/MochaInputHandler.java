package com.util;
import com.app.Main;
import com.util.debug.MochaNotif;

import static org.lwjgl.glfw.GLFW.*;
import java.util.*;
public class MochaInputHandler {
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

    public MochaInputHandler(long window){
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

    public void setKey(String command, int newKey) {
        int size = commands.size();
        int commandIndex = -1;
        Command newCommand = null;

        /* TODO: check if newKey is in the GLFW values - output MochaNotif error if not found */

        // checks through the LinkedList to find the command to be changed
        for ( int i = 0; i < size; ++i ) {
            if ( commands.get(i).getCommand().equalsIgnoreCase(command) ) {
                commandIndex = i;
                newCommand = new Command(command, newKey);
                commands.set(commandIndex, newCommand);
            }
        }

        // if the requested command was not found - the new key isn't set and a MochaNotif pops up
        if ( commandIndex == -1 ) {
            MochaNotif errNotif = new MochaNotif();
            String message = "The command: '" + command + "' was not found, the new key was not set.";
            String title = "Command Not Found";
            errNotif.show(message, title);
        }
    }
    
    public static void main(String args[]) {
    	Window win = new Window("MochaInterpreter", 1280, 720);
    	MochaInputHandler input = new MochaInputHandler(win.getWindow());
    	
    	Main.gameLoop(win);
    }

  public static  void key_callback(long window, int key, int scancode, int action, int mods)
    {
        if (key == GLFW_KEY_E && action == GLFW_PRESS)
            System.out.println("E was pressed");
        if (key == GLFW_KEY_D && action == GLFW_PRESS)
            System.out.println("D was pressed");

        if (key == GLFW_KEY_W && action == GLFW_PRESS)
            System.out.println("W was pressed");
        if (key == GLFW_KEY_A && action == GLFW_PRESS)
            System.out.println("A was pressed");
        if (key == GLFW_KEY_S && action == GLFW_PRESS)
            System.out.println("S was pressed");}
  		
}