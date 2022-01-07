package State;

import Hardware.*;
import Logic.DriveControl;
import TI.Timer;
import Tools.Remote_Buttons;

import java.awt.*;

/**
 * The state where to bot detects collisions
 */
public class LinefollowerState extends ControlState {

    /** Construct a LinefollowerState
     * @param callback Where should be called back to for switching state
     */
    public LinefollowerState(Hardware hardware, DriveControl callback) {
        super(hardware, callback);
        resetLed();
    }

    @Override
    public void resetLed(){
        this.hardware.getFrontLeftLed().setColor(Color.white);
        this.hardware.getFrontLeftLed().turnOn();
        this.hardware.getBackLeftLed().setColor(Color.red);
        this.hardware.getBackLeftLed().turnOn();
        this.hardware.getFrontRightLed().setColor(Color.white);
        this.hardware.getFrontRightLed().turnOn();
        this.hardware.getBackRightLed().setColor(Color.red);
        this.hardware.getBackRightLed().turnOn();
        this.hardware.getFrontMiddleLed().setColor(Color.green);
        this.hardware.getFrontMiddleLed().turnBlinkOn();
        this.hardware.getBackMiddleLed().setColor(Color.green);
        this.hardware.getBackMiddleLed().turnBlinkOn();
    }

    //TODO: Temporary timer for checking if the button is pressed
    private Timer tempTimer = new Timer(100);

    @Override
    public void drive() {
        this.hardware.getBuzzer().setBuzzing(false);

        this.hardware.getServoControl().update();

        int number = 0;

        while (!this.hardware.getES().isPressed()) {
            this.hardware.getServoControl().instantStop();
            this.driving = true;
            buzzerToggle(false);
            if (tempTimer.timeout()) {
                System.out.println("LF: " + number++);
                tempTimer.mark();
            }
        }

        if (!this.hardware.getES().isPressed() && this.driving) {
            this.hardware.getServoControl().stop();
        }

    }


    public void Linehandler(int left,int right){
        this.hardware.getRightServo().setLinefollowmode(true);
        this.hardware.getLeftServo().setLinefollowmode(true);
        if(left > 1000  || right > 1000) {
            if (left > 1000) {
//                this.hardware.getServoControl().turn(Direction.LEFT, 3);
                System.out.println("turning right");
                this.hardware.getRightServo().instantUpdate(1450);
                this.hardware.getLeftServo().instantUpdate(1450);
            } else {
//                this.hardware.getServoControl().turn(Direction.RIGHT, 3);
                System.out.println("turning left");
                this.hardware.getRightServo().instantUpdate(1550);
                this.hardware.getLeftServo().instantUpdate(1550);
            }

        } else {
            this.hardware.getRightServo().instantUpdate(1450);
            this.hardware.getLeftServo().instantUpdate(1550);
        }

    }

    @Override
    public void onRemotePress(int value) {

        if (value != Remote_Buttons.BUTTON_ASPECT) { //191
            this.hardware.getRightServo().setLinefollowmode(false);
            this.hardware.getLeftServo().setLinefollowmode(false);
            System.out.println("Detected remote in LF");
            InfraRedState infraRedState = new InfraRedState(this.hardware, this.callback);
            infraRedState.onRemotePress(value);
            callback.switchState(infraRedState);
        } else {
            System.out.println("Already in LF");
        }

    }

    @Override
    public void onButtonPress() {
        System.out.println("Pressed button");
    }

    @Override
    public void onBTSignal(Bluetooth source, int value) {
        System.out.println("Received Bluetooth signal");
    }

    @Override
    public void onLineFollowTrigger(LineFollower source, int rightfollow, int leftfollow) {
        System.out.println("Triggered line follower | R: " + rightfollow + " | L: "+ leftfollow);
        Linehandler(leftfollow,rightfollow);
    }

    @Override
    public void onUltrasoneTrigger(Ultrasone source, int value) {
        System.out.println("Triggered ultrasone");
        onRemotePress(5);
    }
}