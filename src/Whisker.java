import TI.BoeBot;
import TI.PinMode;
import TI.Timer;

public class Whisker implements Updatable {

    private int pin;
    private boolean currentState;
    private Timer timer = new Timer(200);

    public Whisker(int pin) {
        this.pin = pin;
        BoeBot.setMode(pin, PinMode.Input);

    }

    public boolean isPressed() {
        this.currentState = !BoeBot.digitalRead(this.pin);
        //System.out.println("pin: " + this.pin + this.currentState);
        return this.currentState;
    }

    @Override
    public void Update() {
        if(isPressed()&& timer.timeout()){
            timer.mark();
            //System.out.println("whisker on pin: " + this.pin + " is pressed");
        }

    }

}
