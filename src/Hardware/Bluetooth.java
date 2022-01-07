package Hardware;

import Interface.BTCallback;
import Interface.Update;
import TI.SerialConnection;


/**
 * A class for Bluetooth
 */
public class Bluetooth implements Update {
   private SerialConnection serial;
   private BTCallback callback;

    /**
     * Construct Bluetooth
     */
    public Bluetooth() {
        this.serial = new SerialConnection(115200);
    }

    public void setCallback(BTCallback btCallback) {
        this.callback = btCallback;
    }

    @Override
        public void update() {
        if (serial.available() > 0) {
            int data = serial.readByte();
            serial.writeByte(data); // Echo data
            if(data > 13) {
                System.out.println("Received: " + data);
                callback.onBTSignal(this, data);
            }
        }
    }

}
