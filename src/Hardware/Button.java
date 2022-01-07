package Hardware;

import Interface.ButtonCallback;
import Interface.Update;
import TI.BoeBot;
import TI.PinMode;
import TI.Timer;

//TODO: I am still not satisfied by the button. Use a callback

/**
 * A hardware button.
 */
public class Button implements Update {

    private int pin;
    private boolean currentState;
    private Timer updateTimer = new Timer(500);

    private ButtonCallback callback;

    /** Construct a button
     * @param pin Which pin is connected to the button
     */
    public Button(int pin) {
        this.pin = pin;
        this.currentState = false;
        BoeBot.setMode(this.pin, PinMode.Input);
    }

    public void setCallback(ButtonCallback buttonCallback) {
        this.callback = buttonCallback;
    }

    /** A method to check if the button is pressed.
     * @return The value of the button
     */
    public boolean isPressed() {
        if (!BoeBot.digitalRead(this.pin) && updateTimer.timeout()) {
            this.currentState = !this.currentState;
            updateTimer.mark();
            //System.out.println(currentState);
        }

        return this.currentState;
    }

    /** Get the state of the button
     * @return The real-time value of the button
     */
    public boolean getState(){
        return !BoeBot.digitalRead(this.pin);
    }

    @Override
    public void update() {
//        if(isPressed()){
//            callback.onButtonPress();
//        }
    }

}
