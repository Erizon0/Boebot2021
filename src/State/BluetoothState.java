package State;

import Hardware.Bluetooth;
import Hardware.Hardware;
import Hardware.LineFollower;
import Hardware.Ultrasone;
import Logic.DriveControl;
import TI.Timer;
import Tools.BluetoothControls;
import Tools.Direction;
import Tools.Remote_Buttons;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class for he bluetoothstate and all of its logic
 */
public class BluetoothState extends ControlState {

    /** Method for constructing a BleutoothState
     * @param hardware The hardware class with all hardware in it
     * @param callback Where the state should call back to when switching ot a different state
     */
    public BluetoothState(Hardware hardware, DriveControl callback) {
        super(hardware, callback);
        resetLed();
    }

    //Timer for checking if the button is pressed
    private Timer tempTimer = new Timer(100);

    private boolean isReceivingArray;
    private ArrayList<BluetoothControls> values = new ArrayList<>();

    @Override
    public void drive() {
        this.hardware.getServoControl().update();

        int number = 0;

        while (!this.hardware.getES().isPressed()) {
            this.hardware.getServoControl().instantStop();
            if (tempTimer.timeout()) {
                System.out.println("BT: " + number++);
                tempTimer.mark();
            }
        }

        if (!this.hardware.getES().isPressed() && this.driving) {
            this.hardware.getServoControl().stop();
        }
    }


    /** Receives bluetooth signals and acts by the values given
     * @param bluetoothValue value received by bluetooth
     */
    public void bluetoothHandler(int bluetoothValue) {
        //checks if the array has to initiated or not
        if (bluetoothValue == BluetoothControls.STARTARRAY.getNumber()) {
            values.clear();
            isReceivingArray = true;
        } else if (bluetoothValue == BluetoothControls.STOPARRAY.getNumber()) {
            isReceivingArray = false;
            //TODO add execute array
        }

        if (isReceivingArray && bluetoothValue != BluetoothControls.STARTARRAY.getNumber()){
            values.add(BluetoothControls.numberOf(bluetoothValue));
        }

    }

    @Override
    public void onRemotePress(int value) {
        if (value != Remote_Buttons.BUTTON_TELETEXT) { //182
            System.out.println("Detected remote in BT");
            InfraRedState infraRedState = new InfraRedState(this.hardware, this.callback);
            infraRedState.onRemotePress(value);
            callback.switchState(infraRedState);
        } else {
            System.out.println("Already in BT");
        }
    }

    @Override
    public void resetLed() {
        this.hardware.getFrontLeftLed().setColor(Color.white);
        this.hardware.getFrontLeftLed().turnOn();
        this.hardware.getBackLeftLed().setColor(Color.red);
        this.hardware.getBackLeftLed().turnOn();
        this.hardware.getFrontRightLed().setColor(Color.white);
        this.hardware.getFrontRightLed().turnOn();
        this.hardware.getBackRightLed().setColor(Color.red);
        this.hardware.getBackRightLed().turnOn();
        this.hardware.getFrontMiddleLed().setColor(Color.blue);
        this.hardware.getFrontMiddleLed().turnBlinkOn();
        this.hardware.getBackMiddleLed().setColor(Color.blue);
        this.hardware.getBackMiddleLed().turnBlinkOn();
    }

    @Override
    public void onButtonPress() {
    }

    @Override
    public void onBTSignal(Bluetooth source, int value) {
        bluetoothHandler(value);
    }

    @Override
    public void onLineFollowTrigger(LineFollower source, int rightfollow, int leftfollow) {
    }

    @Override
    public void onUltrasoneTrigger(Ultrasone source, int value) {
        System.out.println("Triggered ultrasone");
    }

}
