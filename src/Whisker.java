import TI.BoeBot;

public class Whisker implements Updatable {

    private int pin;
    private boolean currentState;

    public Whisker(int pin) {
        this.pin = pin;
    }

    public boolean isPressed() {
        return currentState;
    }

    @Override
    public void Update() {
        currentState = BoeBot.digitalRead(this.pin);
    }

}
