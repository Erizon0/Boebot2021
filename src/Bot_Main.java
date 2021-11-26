import Hardware.*;
import Hardware.Button;
import Interface.Update;
import TI.*;

import java.awt.*;
import java.util.ArrayList;

public class Bot_Main {

    private DriveControl driveControl;
    private ArrayList<Update> updatables = new ArrayList<>();
    private Button button1;
    private Button button2;
    private Buzzer buzzer;
    private Timer timer;
    private Led led;

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
//        ServoMotor leftServoMotor = new ServoMotor(13, -1);
//        ServoMotor rightServoMotor = new ServoMotor(12,1);
//
//        Whisker leftWhisker = new Whisker(3);
//        Whisker rightWhisker = new Whisker(4);
        BoeBot.setMode(10,PinMode.Input);
        this.button1 = new Button(1);
        this.button2 = new Button(2);
        this.timer = new Timer(500);
        led = new Led(1, Color.red,250);
 //       this.driveControl = new DriveControl(leftServoMotor, rightServoMotor, leftWhisker, rightWhisker);

        BoeBot.setMode(7, PinMode.Input);
    }

    /*
    loop for running everything
     */
    public void run() throws InterruptedException{
        while (true) {
            buzzer = new Buzzer(10,1000,250);

//            buzzer.update();
            led.update();
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
