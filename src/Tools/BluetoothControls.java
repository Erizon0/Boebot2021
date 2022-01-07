package Tools;

import Hardware.Bluetooth;
import javafx.scene.effect.Blend;

import java.util.HashMap;
import java.util.Map;

/** Enums for the different kinds of bluetooth signals
 */
public enum BluetoothControls {
    FORWARDS(119),
    BACKWARDS(115),
    LEFT(97),
    RIGHT(100),
    EBRAKE(113),
    GRABBER(184),
    STOPARRAY(9),
    STARTARRAY(0);

    private int number;
    private static Map map = new HashMap<>();


    /** Constructor for BluetoothControls
     * @param number The number associated with the corresponding bluetooth signal
     */
    BluetoothControls(int number) {
        this.number = number;
    }

    static {
        for (BluetoothControls bluetoothControls : BluetoothControls.values()){
            map.put(bluetoothControls.number, bluetoothControls);
        }
    }

    /** Method for converting an integer to enum
     * @param number The signal number sent by bluetooth
     * @return The enum that matches the number
     */
    public static BluetoothControls numberOf(int number){
        return (BluetoothControls) map.get(number);
    }

    public int getNumber() {
        return number;
    }

    }

