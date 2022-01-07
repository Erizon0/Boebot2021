package Logic;

import Hardware.*;
import Hardware.Button;
import Interface.*;
import State.ControlState;
import State.InfraRedState;

import javax.naming.ldap.Control;
import java.awt.*;

//TODO: Make sure every piece of hardware initializes here and nowhere else

/**
 * The class where everything gets updated and constructed
 */
public class DriveControl implements Update, DriveState, ServoCallback, IRCallback, ButtonCallback, BTCallback, LFCallback, UltrasoneCallback {

    private Whisker leftWhisker;
    private Whisker rightWhisker;
    private ControlState driveState;

    private ServoMotor leftServoMotor;
    private ServoMotor rightServoMotor;

    private Led backLeftLed;
    private Led backRightLed;
    private Led frontLeftLed;
    private Led frontRightLed;

    private Led frontMiddleLed;
    private Led backMiddleLed;

    private InfraRed infraRed;

    private LineFollower lineFollower;

    private Ultrasone ultrasone;

    private Bluetooth bluetooth;

    private Button button;
    private Buzzer buzzer;

    private Hardware hardware;

    /**
     * Construct a DriveControl
     */
    public DriveControl() {
        this.backLeftLed =  new Led(2, Color.red, 500);
        this.backRightLed = new Led(0, Color.red, 500);
        this.frontLeftLed = new Led(3, Color.white, 500);
        this.frontRightLed = new Led(5, Color.white, 500);

        this.frontMiddleLed = new Led(4, Color.white, 500);
        this.backMiddleLed = new Led(1, Color.white, 500);

        this.leftWhisker = new Whisker(3);
        this.rightWhisker = new Whisker(4);

        this.infraRed = new InfraRed(14);

        this.lineFollower = new LineFollower(0, 1);

        this.ultrasone = new Ultrasone(2, 1);

        this.bluetooth = new Bluetooth();

        this.rightServoMotor = new ServoMotor(13, -1);
        this.leftServoMotor = new ServoMotor(12, 1);

        this.buzzer = new Buzzer(8, 200, 300);
        this.button = new Button(0);

        this.hardware = new Hardware(this.button, this.leftServoMotor, this.rightServoMotor, this.buzzer, this.infraRed, this.frontRightLed,
                this.frontLeftLed, this.backRightLed, this.backLeftLed, this.frontMiddleLed, this.backMiddleLed, this.lineFollower, this.bluetooth , this.ultrasone);
        this.driveState = new InfraRedState(this.hardware, this);

        this.hardware.setSCCallback(this);
        this.hardware.setIRCallback(this);
        this.hardware.setButtonCallback(this);
        this.hardware.setBTCallback(this);
        this.hardware.setLFCallback(this);
        this.hardware.setUltrasoneCallback(this);
    }

    /**
     * @param driveState The new state that should be switched to
     */
    public void switchState(ControlState driveState) {
        this.driveState = driveState;
    }

    @Override
    public void update() {
        this.hardware.update();
        this.driveState.drive();
    }

    @Override
    public void onBTSignal(Bluetooth source, int value) {
        this.driveState.onBTSignal(source, value);
    }

    @Override
    public void onButtonPress() {
        this.driveState.onButtonPress();
    }

    @Override
    public void drive() {
        this.driveState.drive();
    }

    @Override
    public void onRemotePress(int value) {
        this.driveState.onRemotePress(value);
    }

    @Override
    public void onLineFollowTrigger(LineFollower source, int rightValue, int leftValue) {
        this.driveState.onLineFollowTrigger(source, rightValue, leftValue);
    }

    @Override
    public void turnLeftOn() {
        this.driveState.turnLeftOn();
    }

    @Override
    public void turnLeftOff() {
        this.driveState.turnLeftOff();
    }

    @Override
    public void turnRightOn() {
        this.driveState.turnRightOn();
    }

    @Override
    public void turnRightOff() {
        this.driveState.turnRightOff();
    }

    @Override
    public void buzzerToggle(boolean bool) {
        this.driveState.buzzerToggle(bool);
    }

    @Override
    public void resetLed() {
        this.driveState.resetLed();
    }

    @Override
    public void setDriving(boolean bool) {
        this.driveState.setDriving(bool);
    }

    @Override
    public void onUltrasoneTrigger(Ultrasone source, int value) {
        this.driveState.onUltrasoneTrigger(source, value);
    }
}