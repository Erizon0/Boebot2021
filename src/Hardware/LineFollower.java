package Hardware;

import Interface.LFCallback;
import Interface.Update;
import TI.BoeBot;
import TI.Timer;


/**
 * A class for two linefollowers, calls back when they detect a line
 */
public class LineFollower implements Update {
    private int rightPin;
    private int leftPin;
    private LFCallback callback;

    private Timer timer = new Timer(50);

    /** A constructor for LineFollower
     * @param leftPin The number of the pin where the left linefollower is connected to
     * @param rightPin The number of the pin where the right linefollower is connected to
     */
    public LineFollower(int leftPin, int rightPin) {
        this.leftPin = leftPin;
        this.rightPin = rightPin;
    }

    /** Setes the callback of the linfollowers
     * @param LFCallback Where Linfollower should call back to
     */
    public void setCallback(LFCallback LFCallback) {
        this.callback = LFCallback;
    }

    @Override
    public void update() {
        if (timer.timeout()) {
            if (BoeBot.analogRead(leftPin) > 500 || BoeBot.analogRead(rightPin) > 500 ) {
                callback.onLineFollowTrigger(this, BoeBot.analogRead(leftPin), BoeBot.analogRead(rightPin));
            }
        }
    }
}



