package State;

import Hardware.*;
import Interface.*;
import Logic.DriveControl;

import java.awt.*;

/** Base class for all drive states
 */
public abstract class ControlState implements DriveState, ServoCallback, IRCallback, ButtonCallback, BTCallback, LFCallback, UltrasoneCallback {

    protected Hardware hardware;
    protected DriveControl callback;
    protected boolean driving = true;


    /** Constructor for ControlState
     * @param hardware All the hardware
     * @param driveControl The callback for state switching
     */
    public ControlState(Hardware hardware, DriveControl driveControl) {
        this.hardware = hardware;
        this.callback = driveControl;
    }

    @Override
    public abstract void drive();

    @Override
    public abstract void onBTSignal(Bluetooth source, int value);

    @Override
    public abstract void onButtonPress();

    @Override
    public abstract void onRemotePress(int value);

    @Override
    public abstract void onLineFollowTrigger(LineFollower source, int rightValue, int leftValue);

    @Override
    public void turnRightOn() {
        this.hardware.getFrontLeftLed().turnBlinkOff();
        this.hardware.getBackLeftLed().turnBlinkOff();
        this.hardware.getFrontRightLed().setColor(Color.orange);
        this.hardware.getFrontRightLed().turnBlinkOn();
        this.hardware.getBackRightLed().setColor(Color.orange);
        this.hardware.getBackRightLed().turnBlinkOn();
    }

    @Override
    public void turnRightOff() {
        this.hardware.getFrontLeftLed().turnOn();
        this.hardware.getBackLeftLed().turnOn();
        this.hardware.getFrontRightLed().setColor(Color.white);
        this.hardware.getFrontRightLed().turnOn();
        this.hardware.getBackRightLed().setColor(Color.red);
        this.hardware.getBackRightLed().turnOn();
    }

    @Override
    public void turnLeftOn() {
        this.hardware.getFrontLeftLed().setColor(Color.orange);
        this.hardware.getFrontLeftLed().turnBlinkOn();
        this.hardware.getBackLeftLed().setColor(Color.orange);
        this.hardware.getBackLeftLed().turnBlinkOn();
        this.hardware.getFrontRightLed().turnBlinkOff();
        this.hardware.getBackRightLed().turnBlinkOff();
    }

    @Override
    public void turnLeftOff() {
        this.hardware.getFrontLeftLed().turnOn();
        this.hardware.getFrontLeftLed().setColor(Color.white);
        this.hardware.getBackLeftLed().turnOn();
        this.hardware.getBackLeftLed().setColor(Color.red);
        this.hardware.getFrontRightLed().turnOn();
        this.hardware.getBackRightLed().turnOn();
    }

    @Override
    public void buzzerToggle(boolean bool) {
        this.hardware.getBuzzer().setBuzzing(bool);
    }

    @Override
    public abstract void resetLed();

    @Override
    public void setDriving(boolean bool) {
        this.driving = bool;
    }

    @Override
    public abstract void onUltrasoneTrigger(Ultrasone source, int value);
}
