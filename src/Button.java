import TI.BoeBot;

public class Button implements Updatable {

    private int pin;
    private boolean currentState;

    public Button(int pin) {
        this.pin = pin;
        this.currentState = false;
    }

    public boolean isPressed() {
        return this.currentState;
    }

    @Override
    public void Update() {
        if(!BoeBot.digitalRead(this.pin)){
            this.currentState = !this.currentState;
        }
    }

}
