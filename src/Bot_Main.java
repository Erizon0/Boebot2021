import Interface.Update;
import Logic.DriveControl;
import TI.BoeBot;

import java.util.ArrayList;

/**
 * The main class of the bot
 */
public class Bot_Main {

    private DriveControl driveControl;

    /** The first method run
     * @param args The arguments that were given
     */
    public static void main(String[] args) {
        Bot_Main bm = new Bot_Main();
        bm.init();
        bm.run();
    }

    /**
     * Method for initializing everything
     */
    public void init(){
        this.driveControl = new DriveControl();
    }

    /**
     * Loop for running everything
     */
    public void run() {
        while (true) {
            this.driveControl.update();
//            BoeBot.wait(1);
        }
    }

}
