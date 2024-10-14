package com.util.debug;

/**
 * <p>Debug Thread Class</p>
 * <p>Runs a Log Thread alongside the main Applicaton. Utilizes Error Messages and MochaLogs.</p>
 */
public class DebugThread implements Runnable{
    private MochaLogs mLogs;
    private MochaNotif mNotif;

    private boolean exceptionThrown;

    public DebugThread(){
        mLogs = new MochaLogs();
        mNotif = new MochaNotif();
        exceptionThrown = false;
    }
    @Override
    public void run() {
        // The processes that help with debugging...
        try {
            // Here will be code that throws one of the exceptions if applicable

            // Below is code used to test the triggering of the Debugging system
            // throw new RuntimeException("Test");

            // Below is code used to test the triggering of the Debugging system again
            // throw new Exception("Text");

        } catch (RuntimeException ex) {
            notify("Runtime Exception was thrown", "Runtime Exception");
            runtimeExceptionMessage(ex);
            exceptionThrown = true;

        } catch (Exception ex) {
            notify("Exception was thrown", "Exception");
            exceptionMessage(ex);
            exceptionThrown = true;
        }
    }

    public void log(String message){
        mLogs.log(message);
    }

    public void exceptionMessage(Exception ex){
        mLogs.exceptionMessage(ex);
    }

    public void runtimeExceptionMessage(RuntimeException ex){
        mLogs.runtimeExceptionMessage(ex);
    }



    public void notify(String message, String title){
        mNotif.show(message, title);
    }

    public int numExceptions(){
        return mLogs.getNumExceptions();
    }

    public boolean isExceptionThrown(){
        return this.exceptionThrown;
    }


}