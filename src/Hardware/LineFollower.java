package Hardware;

import Interface.LFCallback;
import Interface.Update;
import TI.BoeBot;
import TI.Timer;


public class LineFollower implements Update {
    private int rightPin;
    private int leftPin;
    private LFCallback callback;

    private Timer timer = new Timer(50);


    public LineFollower(int leftPin, int rightPin) {
        this.leftPin = leftPin;
        this.rightPin = rightPin;
    }

    public void setCallback(LFCallback LFCallback) {
        this.callback = LFCallback;
    }

    @Override
    public void update() {
        if (timer.timeout()) {
            if (BoeBot.analogRead(leftPin) > 500||BoeBot.analogRead(rightPin) > 500 ) {
                callback.onLineFollowTrigger(this, BoeBot.analogRead(leftPin), BoeBot.analogRead(rightPin));
            }

        }
    }
}



