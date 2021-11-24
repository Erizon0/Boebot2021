import TI.BoeBot;

public class Button implements Updatable {

    private int pin;
    private boolean currentState;

    public Button(int pin) {
        this.pin = pin;
        this.currentState = true;
    }

    public boolean isPressed() {
        if(!BoeBot.digitalRead(0)){
            this.currentState = !this.currentState;
        }
        return this.currentState;
    }

    @Override
    public void Update() {

        if(!BoeBot.digitalRead(this.pin)){
            this.currentState = !this.currentState;
        }
    }

}
