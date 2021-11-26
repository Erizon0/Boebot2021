import Hardware.*;
import TI.BoeBot;

import java.util.ArrayList;

public class Bot_Main {

    private DriveControl driveControl;
    private ArrayList<Update> updatables = new ArrayList<>();


    public static void main(String[] args) {
        Bot_Main bm = new Bot_Main();
        try{
            bm.init();
            bm.run();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /*
    method for initialsing everything
     */
    public void init(){
        ServoMotor leftServoMotor = new ServoMotor(13, -1);
        ServoMotor rightServoMotor = new ServoMotor(12,1);

        Whisker leftWhisker = new Whisker(3);
        Whisker rightWhisker = new Whisker(4);

        this.driveControl = new DriveControl(leftServoMotor, rightServoMotor, leftWhisker, rightWhisker);
    }

    /*
    loop for running everything
     */
    public void run() throws InterruptedException{
        while (true) {
//            for(Update update : this.updatables )
//                update.update();

            //todo remove VVVVVVVVVVV
            this.driveControl.update();
            BoeBot.wait(5);
        }
    }

//    //We need to have a place to initialize all hardware
//    public static void main(String[] args) {
//
//        ServoMotor leftServoMotor = new ServoMotor(13, -1);
//        ServoMotor rightServoMotor = new ServoMotor(12,1);
//
//        Whisker leftWhisker = new Whisker(3);
//        Whisker rightWhisker = new Whisker(4);
//
//        DriveControl driveControl = new DriveControl(leftServoMotor, rightServoMotor, leftWhisker, rightWhisker);
//
//        while (true) {
//            driveControl.update();
//            BoeBot.wait(5);
//        }
//
//    }

}
