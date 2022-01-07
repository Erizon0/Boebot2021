package State;

import Hardware.*;
import Logic.DriveControl;
import TI.Timer;
import Tools.Direction;
import Tools.Remote_Buttons;

import java.awt.*;

/**
 * The state where to bot handles infrared signals
 */
public class InfraRedState extends ControlState {

    private boolean ultrasoneState = true;

    /** Construct a InfraRedState
     * @param callback Where should be called back to for switching state
     */
    public InfraRedState(Hardware hardware, DriveControl callback) {
        super(hardware, callback);
        resetLed();
    }

    @Override
    public void buzzerToggle(boolean bool) {
        this.hardware.getBuzzer().setBuzzing(bool);
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
        this.hardware.getFrontMiddleLed().setColor(new Color(128, 0, 128));
        this.hardware.getFrontMiddleLed().turnBlinkOn();
        this.hardware.getBackMiddleLed().setColor(new Color(128, 0, 128));
        this.hardware.getBackMiddleLed().turnBlinkOn();
    }

    //Timer for checking if the button is pressed
    private Timer tempTimer = new Timer(100);

    @Override
    public void drive() {

        this.hardware.getServoControl().update();

        int number = 0;

        while (!this.hardware.getES().isPressed()) {
            this.hardware.getServoControl().instantStop();
            resetLed();
            this.hardware.getBuzzer().setBuzzing(false);

            if (tempTimer.timeout()) {
                System.out.println("IR: " + number++);
                tempTimer.mark();
//                this.hardware.getGripper().open();
            }
        }

//        if (!this.hardware.getES().isPressed() && this.driving) {
//
////            this.hardware.getServoControl().stop();
//
//        }
    }

    /** Handle remote signal values
     * @param value The number that got sent by the remote
     */
    public void buttonHandler(int value) {
        if (value == Remote_Buttons.BUTTON_POWER) {
            this.hardware.getServoControl().instantStop();
            this.hardware.getBuzzer().setBuzzing(false);
            resetLed();
            System.out.println("Emergency stop");
        } else if (value == Remote_Buttons.BUTTON_CH_UP) {
            this.hardware.getServoControl().goToSpeed(5);
            this.hardware.getBuzzer().setBuzzing(false);
            System.out.println("Forward");
        } else if (value == Remote_Buttons.BUTTON_CH_DOWN) {
            this.hardware.getServoControl().goToSpeed(-3);
            this.hardware.getBuzzer().setBuzzing(true);
            System.out.println("Backward");
        } else if (value == Remote_Buttons.BUTTON_VOL_UP) {
            this.hardware.getBuzzer().setBuzzing(false);
            this.hardware.getServoControl().timeTurn(2000, Direction.RIGHT);
            System.out.println("Turn right");
        } else if (value == Remote_Buttons.BUTTON_VOL_DOWN) {
            this.hardware.getBuzzer().setBuzzing(false);
            this.hardware.getServoControl().timeTurn(2000, Direction.LEFT);
            System.out.println("Turn left");
        } else if (value == Remote_Buttons.BUTTON_0) {
            this.hardware.getBuzzer().setBuzzing(false);
            this.hardware.getServoControl().stop();
            System.out.println("Stopping");
        } else if (value == Remote_Buttons.BUTTON_ASPECT) {
            this.hardware.getBuzzer().setBuzzing(false);
            System.out.println("Switch to LF");
            this.callback.switchState(new LinefollowerState(this.hardware, this.callback));
        } else if (value == Remote_Buttons.BUTTON_TELETEXT) {
            this.hardware.getBuzzer().setBuzzing(false);
            System.out.println("Switch to BT");
            this.callback.switchState(new BluetoothState(this.hardware, this.callback));
        } else if (value == Remote_Buttons.BUTTON_REC) {
            //Turn ultrasone off
            this.ultrasoneState = false;
        } else if (value == Remote_Buttons.BUTTON_STOP) {
            //Turn ultrasone on
            this.ultrasoneState = true;
        } else if (value == Remote_Buttons.BUTTON_EMPTY_BOX) {
            System.out.println("Move the gripper");
            this.hardware.getGripper().gripperMove();
        } else if (value == Remote_Buttons.BUTTON_1) {
            System.out.println("Go to gear 1");
            this.hardware.getServoControl().goToSpeed(1);
            this.hardware.getBuzzer().setBuzzing(false);
        } else if (value == Remote_Buttons.BUTTON_2) {
            System.out.println("Go to gear 2");
            this.hardware.getServoControl().goToSpeed(2);
            this.hardware.getBuzzer().setBuzzing(false);
        } else if (value == Remote_Buttons.BUTTON_3) {
            System.out.println("Go to gear 3");
            this.hardware.getServoControl().goToSpeed(3);
            this.hardware.getBuzzer().setBuzzing(false);
        } else if (value == Remote_Buttons.BUTTON_4) {
            System.out.println("Go to gear 4");
            this.hardware.getServoControl().goToSpeed(4);
            this.hardware.getBuzzer().setBuzzing(false);
        } else if (value == Remote_Buttons.BUTTON_5) {
            System.out.println("Go to gear 5");
            this.hardware.getServoControl().goToSpeed(5);
            this.hardware.getBuzzer().setBuzzing(false);
        } else if (value == Remote_Buttons.BUTTON_6) {
            System.out.println("Go to gear 6");
            this.hardware.getServoControl().goToSpeed(6);
            this.hardware.getBuzzer().setBuzzing(false);
        } else {
            System.out.println("Bad code");
        }
    }

    @Override
    public void onRemotePress(int value) {
        buttonHandler(value);
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
    public void onLineFollowTrigger(LineFollower source, int rightValue, int leftValue) {

    }

    @Override
    public void onUltrasoneTrigger(Ultrasone source, int value) {
        if(this.ultrasoneState){
            System.out.println("Triggered ultrasone");
            this.hardware.getServoControl().instantStop();
        }

    }
}
