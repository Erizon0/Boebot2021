import TI.BoeBot;
import TI.Timer;

public class Whisker implements Updatable {

    private int pin;
    private boolean currentState;
    private Timer timer;

    public Whisker(int pin) {
        this.pin = pin;

    }

    public boolean isPressed() {
        this.currentState = BoeBot.digitalRead(this.pin);
        return this.currentState;
    }

    @Override
    public void Update() {
        if(isPressed()){
            System.out.println("whisker on pin: "+ this.pin+" is pressed");
        }

    }

}
