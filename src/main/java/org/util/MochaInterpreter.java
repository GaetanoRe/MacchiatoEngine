package org.util;

import java.io.*;

/**
 * <p>Name: MochaInterpreter Class</p>
 * <p>Description: Reads .mocha files and utilizes them based on their names</p>
 */
public class MochaInterpreter {
    private String filepath = "org/sys/settings/";
    private String filename;
    private File file;
    public MochaInterpreter(String filename){
        this.filepath += filename + ".mocha";
        this.file = new File(this.filepath);
        this.filename = file.getName();
    }

    /**
     * interperate method - takes the file loaded into the Mocha Interpretor and creates the data structure assigned
     * with the class
     * @throws IOException
     */
    public void Interpret() throws IOException {
        String[] splitName = filename.split(".");
        if(splitName[1].equals("mocha")){
            // Now, read the mocha file and if the splitname[0] says input, use the InputHandler class and add them to
            // InputHandler LinkedList (might change it to a heap but it is a linked list for now)
        }
        else{
            throw new IOException("This is not a mocha file");
        }

    }
}
