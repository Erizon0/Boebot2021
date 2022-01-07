package Hardware;

import Interface.Update;
import TI.BoeBot;
import TI.PinMode;
import TI.Timer;

/**
 * A hardware whisker
 */
public class Whisker implements Update {

    private int pin;
    private boolean currentState;
    private Timer updateTimer = new Timer(200);

    /** Construct a whisker
     * @param pin What pin the whisker is on
     */
    public Whisker(int pin) {
        this.pin = pin;
        this.currentState = false;
        BoeBot.setMode(pin, PinMode.Input);
    }

    /** Check if the whisker is pressed
     * @return The state of the whisker
     */
    public boolean isPressed() {
        return this.currentState;
    }

    @Override
    public void update() {
        if (!BoeBot.digitalRead(this.pin)) {
            this.currentState = true;
            this.updateTimer.mark();
        } else if (updateTimer.timeout()){
            this.currentState = false;
        }
    }

}
