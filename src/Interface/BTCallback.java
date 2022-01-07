package Interface;

import Hardware.Bluetooth;

/**
 * Interface for different callbacks from bluetooth
 */
public interface BTCallback {
    /** Method for handling bluetooth signals
     * @param source Where the signal came from
     * @param value What number was recieved
     */
    void onBTSignal(Bluetooth source, int value);
}
