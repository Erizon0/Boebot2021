package Tools;

import Hardware.Bluetooth;
import javafx.scene.effect.Blend;

import java.util.HashMap;
import java.util.Map;

/**
 * Enums for the different kinds of bluetooth signals
 */
public enum BluetoothControls {
    FORWARDS(119),
    BACKWARDS(115),
    LEFT(97),
    RIGHT(100),
    EBRAKE(113),
    GRABBER(184),
    STARTARRAY(69),
    STOPARRAY(42);

    private int number;
    private static Map<Integer, BluetoothControls> map = new HashMap<>();

    /** Constructor for BluetoothControls
     * @param number The number associated with the corresponding bluetooth signal
     */
    BluetoothControls(int number) {
        this.number = number;
    }

    /**
     * On init, add all enums to a map with their values
     */
    static {
        for (BluetoothControls bluetoothControl : BluetoothControls.values()){
            map.put(bluetoothControl.getNumber(), bluetoothControl);
        }
    }

    /** Method for converting an integer to enum
     * @param number The signal number sent by bluetooth
     * @return The enum that matches the number
     */
    public static BluetoothControls numberOf(int number){
        return map.get(number);
    }

    public int getNumber() {
        return number;
    }

}
