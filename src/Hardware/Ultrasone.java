package Hardware;

import Interface.UltrasoneCallback;
import Interface.Update;
import TI.BoeBot;
import TI.PinMode;
import TI.Timer;


/**
 * A class for Ultrasone
 */
public class Ultrasone implements Update {

    private int triggerPin;
    private int echoPin;
    private int pulse;
    private boolean isDetecting;
    private UltrasoneCallback callback;
    private Timer timer;

    /** Constructor for ultrasonic sensor
     * @param trigPin What PIN is used to receive the reflected pulse that it emits
     * @param echoPin What PIN is used to trigger the ultrasonic pulses
     */
    public Ultrasone (int trigPin, int echoPin) {
        this.triggerPin = trigPin;
        this.echoPin = echoPin;
        this.isDetecting = false;
        this.timer = new Timer(100);

        BoeBot.setMode(trigPin,PinMode.Input);
        BoeBot.setMode(echoPin,PinMode.Output);
    }

    /** Sets the callback for Ultrasone
     * @param ultrasoneCallback Where Ultrasone should call back to
     */
    public void setCallback(UltrasoneCallback ultrasoneCallback) {
        this.callback = ultrasoneCallback;
    }

    /** Class that returns isDetecting
     * @return Whether or not the object is near enough
     */
    public boolean isDetecting() {
        return isDetecting;
    }

    @Override
    public void update() {
        if (timer.timeout()) {
            BoeBot.digitalWrite(this.echoPin, true);

            BoeBot.wait(1);

            BoeBot.digitalWrite(this.echoPin, false);

            this.pulse = BoeBot.pulseIn(this.triggerPin, true, 10000);
            int distance = pulse / 50;

            if (distance <= 20) {
                isDetecting = true;
                callback.onUltrasoneTrigger(this,pulse / 50);
                System.out.println(distance);
            } else {
                isDetecting = false;
            }
            timer.mark();
        }
    }
}



