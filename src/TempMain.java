import Hardware.InfraRed;
import Hardware.Led;
import Interface.IRCallback;
import TI.BoeBot;

import java.awt.*;

/**
 * Temporary main for testing hardware
 */
public class TempMain implements IRCallback {

    public static void main(String[] args) throws InterruptedException {
        TempMain tm = new TempMain();
        tm.start();
    }

    private Led backLeftLed;
    private Led backRightLed;
    private Led frontLeftLed;
    private Led frontRightLed;

    public void start() throws InterruptedException {
        InfraRed infraRed = new InfraRed(14);
        this.backLeftLed =  new Led(2, Color.green, 500);
        this.backRightLed = new Led(0, Color.MAGENTA, 500);
        this.frontLeftLed = new Led(3, Color.red, 500);
        this.frontRightLed = new Led(5, Color.YELLOW, 500);

        this.backLeftLed.turnBlinkOn();
        this.backRightLed.turnBlinkOn();
        this.frontLeftLed.turnBlinkOn();
        this.frontRightLed.turnBlinkOn();

        infraRed.setCallback(this);
        while(true) {
//            BoeBot.wait(1);
            infraRed.update();
            this.frontRightLed.update();
            this.frontLeftLed.update();
            this.backLeftLed.update();
            this.backRightLed.update();
        }

    }

    @Override
    public void onRemotePress(int value) {
        System.out.println("ORP:" + value);
    }
}
