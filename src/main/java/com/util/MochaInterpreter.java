package com.util;

import com.util.debug.MochaNotif;

import java.io.*;
import java.util.Scanner;

/**
 * <p>
 * Name: MochaInterpreter Class
 * </p>
 * <p>
 * Description: Reads .mocha files and utilizes them based on their names
 * </p>
 */
public class MochaInterpreter {
	private String filepath = "resources\\config\\";
	private String filename;
	private File file;
	private MochaInputHandler inputHelper;
	private Scanner scnr;

	public MochaInterpreter(String filename) {
		this.filepath += filename + ".mocha";
		this.file = new File(this.filepath);
		this.filename = file.getName();
	}

	/**
	 * Interpret Method - takes the file loaded into the Mocha Interpreter and
	 * creates the data structure assigned with the class
	 * 
	 * @throws IOException
	 */
	public void Interpret(Window win) throws IOException {
		inputHelper = new MochaInputHandler(win.getWindow());
		String[] splitName = filename.split("\\.");
		System.out.println(splitName.length); // --> 2
		System.out.println(filename); // --> window.mocha
		System.out.println(filepath);

		if (splitName[1].equals("mocha")) {
			// Now, read the mocha file and if the splitname[0] says input, use the
			// InputHandler class and add them to
			// InputHandler LinkedList (might change it to a heap but it is a linked list
			// for now)
			if (splitName[0].equals("input")) {
				System.out.println("printing input");
				setInputSettings(this.file); // edit input settings now
			}
			if (splitName[0].equals("window")) {
				System.out.println("printing window input");
				setWindowSettings(this.file, win);

			}
		} else {
			throw new IOException("This is not a mocha file");
		}
	}

	/*
	 * So far this method only parses through the mocha file however it doesn't do
	 * anything with the information in the file if the line starts with # it skips
	 * the line
	 * 
	 */

	private void setWindowSettings(File input, Window win) throws FileNotFoundException {
		 MochaNotif errNotif = new MochaNotif();
		String message;
		String title;

		String[] splitUp = new String[4]; 
		;
		int index = 0;
		scnr = new Scanner(input);

		System.out.println("\n");
//		printing the mocha file
//		while (scnr.hasNextLine()) {
//			String line = scnr.nextLine();
//			System.out.println(line);
//
//		
//		}

		int width = 0;
		int length = 0;

		while (scnr.hasNextLine()) {
			String line = scnr.nextLine();
			if (line.isEmpty() || line.charAt(0) == '#') {
				// do nothing go to next line
				System.out.println("Empty Line or Comment"); // Could be replaced with errNotif later
				
//				message = "Empty Line or Comment";
//				title = "information";
//				errNotif.show(message, title);
				
				if (scnr.hasNextLine())
					continue;
			} else {
//				System.out.println(line);
				String values[] = line.split(" ");

				if (values[0].equals("WIN_LENGTH"))
					length = Integer.parseInt(values[1]);

				if (values[0].equals("WIN_WIDTH"))
					width = Integer.parseInt(values[1]);
				
				// below is the code previously used, I should probably delete it
//				String values[] = line.split(" ");
//                
//                for (String value: values) {
//                	if (index <splitUp.length) {
//                		splitUp[index++] = value;
//                	}
//                	else { 			// if index is >= 4      avoids exceptions/array out of bound   splitUp array
//                		System.out.println("array is full");		// Could be replaced with errNotif later 
//                		break;

			}
		}

		System.out.println("Width: " + width + "\nLength: " + length);
		//I need to somehow call window.resize(width, height);
		
		win.resizeWindow(width, length);

//        for finding out whats stored in the array 
//        System.out.println("\n\n"); 
//        for (int i=0; i< splitUp.length; i++) {
//        	System.out.println(splitUp[i] + "\t" + i);
//        }

	}

	public void setInputSettings(File input) throws FileNotFoundException {
		/* MochaNotif variables initialized */
		MochaNotif errNotif = new MochaNotif();
		String message;
		String title;

		/* processes input */
		String[] splitUp;
		scnr = new Scanner(input);
		String line = scnr.nextLine();

		/* loop to read the entire input file */
		while (scnr.hasNextLine()) {
			// skip and go to the next line if this line starts with # (comment)
			if (line.charAt(0) == '#') {
				line = scnr.nextLine();
			} else {
				splitUp = line.split(" ");

				// if the line is not in the correct format, inform the user and don't set the
				// command key - continue loop
				/*
				 * if ( splitUp.length != 2 ) { String message =
				 * "An invalid input format was detected: " + line +
				 * "\nThe accepted input format is: '(command) (key)', ."; String title =
				 * "Invalid Format"; errNotif.show(message, title); }
				 */
				if (splitUp.length >= 2) {
					int newKey = Integer.parseInt(splitUp[1]);
					inputHelper.setKey(splitUp[0], newKey);
				}
				line = scnr.nextLine();
			}
		}
	}

	public MochaInputHandler getInputSettings(){
		return inputHelper;
	}

	//used for testing purposes
//	public static void main(String args[]) throws IOException {
//		MochaInterpreter mocha = new MochaInterpreter("window");
//
//		mocha.Interpret();
//	}
	
	
}