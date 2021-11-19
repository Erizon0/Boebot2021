import TI.BoeBot;

public class Button implements Updatable {

    private int pin;
    private boolean currentState;

    public Button(int pin, boolean currentState) {
        this.pin = pin;
        this.currentState = currentState;
    }

    public boolean isPressed() {
        return currentState;
    }

    @Override
    public void Update() {
        currentState = BoeBot.digitalRead(this.pin);
    }

}
