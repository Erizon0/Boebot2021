package Hardware;

import Interface.*;

public class Hardware implements Update {

    private Button ES;

    private ServoMotor leftServo;
    private ServoMotor rightServo;
    private ServoControl servoControl;

    private Buzzer buzzer;

    private InfraRed infraRed;

    private LineFollower lineFollower;

    private Ultrasone ultrasone;

    private Bluetooth bluetooth;

    private Led frontRightLed;
    private Led frontLeftLed;
    private Led backRightLed;
    private Led backLeftLed;

    private Led frontMiddleLed;
    private Led backMiddleLed;
    private Gripper gripper;

    public Hardware(Button ES, ServoMotor leftServo, ServoMotor rightServo, Buzzer buzzer, InfraRed infraRed, Led frontRightLed, Led frontLeftLed, Led backRightLed, Led backLeftLed,
                    Led frontMiddleLed, Led backMiddleLed, LineFollower lineFollower, Bluetooth bluetooth, Ultrasone ultrasone) {
        this.ES = ES;
        this.leftServo = leftServo;
        this.rightServo = rightServo;
        this.servoControl = new ServoControl(this.leftServo, this.rightServo);
        this.buzzer = buzzer;
        this.infraRed = infraRed;

        this.frontRightLed = frontRightLed;
        this.frontLeftLed = frontLeftLed;
        this.backRightLed = backRightLed;
        this.backLeftLed = backLeftLed;

        this.frontMiddleLed = frontMiddleLed;
        this.backMiddleLed = backMiddleLed;

        this.lineFollower = lineFollower;
        this.bluetooth = bluetooth;
        this.ultrasone = ultrasone;
        this.gripper = new Gripper(15);
    }

    public void setUltrasoneCallback(UltrasoneCallback ultrasoneCallback) {
        this.ultrasone.setCallback(ultrasoneCallback);
    }

    public void setBTCallback(BTCallback btCallback) {
        this.bluetooth.setCallback(btCallback);
    }

    public void setLFCallback(LFCallback lfCallback) {
        this.lineFollower.setCallback(lfCallback);
    }

    public void setIRCallback(IRCallback irCallback) {
        this.infraRed.setCallback(irCallback);
    }

    public void setSCCallback(ServoCallback servoCallback) {
        this.servoControl.setCallback(servoCallback);
    }

    public void setButtonCallback(ButtonCallback buttonCallback) {
        this.ES.setCallback(buttonCallback);
    }

    public Button getES() {
        return ES;
    }

    public ServoControl getServoControl() {
        return servoControl;
    }

    public void setServoControl(ServoControl servoControl) {
        this.servoControl = servoControl;
    }

    public ServoMotor getLeftServo() {
        return leftServo;
    }

    public ServoMotor getRightServo() {
        return rightServo;
    }

    public Buzzer getBuzzer() {
        return buzzer;
    }

    public InfraRed getInfraRed() {
        return infraRed;
    }

    public Led getFrontRightLed() {
        return frontRightLed;
    }

    public Led getFrontLeftLed() {
        return frontLeftLed;
    }

    public Led getBackRightLed() {
        return backRightLed;
    }

    public Led getBackLeftLed() {
        return backLeftLed;
    }

    public Led getFrontMiddleLed() {
        return frontMiddleLed;
    }

    public Led getBackMiddleLed() {
        return backMiddleLed;
    }

    public LineFollower getLineFollower() {
        return lineFollower;
    }

    public Ultrasone getUltrasone() {
        return ultrasone;
    }

    public LineFollower getLeftLineFollower() {
        return lineFollower;
    }

    public Bluetooth getBluetooth() {
        return bluetooth;
    }

    public Gripper getGripper(){return this.gripper;}

    @Override
    public void update() {
        this.leftServo.update();
        this.rightServo.update();
        this.backRightLed.update();
        this.frontRightLed.update();
        this.frontLeftLed.update();
        this.backLeftLed.update();
        this.backMiddleLed.update();
        this.frontMiddleLed.update();
        this.buzzer.update();
        this.ES.update();
        this.bluetooth.update();
        this.lineFollower.update();
        this.infraRed.update();
        this.ultrasone.update();
        this.gripper.update();
    }
}
