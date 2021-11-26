package Hardware;

import Interface.Update;
import TI.BoeBot;
import TI.Timer;

public class Button implements Update {

    private int pin;
    private boolean currentState;
    private Timer timer = new Timer(200);

    public Button(int pin) {
        this.pin = pin;
        this.currentState = true;
    }

    public boolean isPressed() {
        getstate();

        if(!BoeBot.digitalRead(0)&&timer.timeout()){
            timer.mark();
            this.currentState = !this.currentState;
        }
        return this.currentState;
    }

    public boolean getstate(){
//        System.out.println(this.currentState);
        return this.currentState;
    }

    @Override
    public void update() {
        getstate();
        if(!BoeBot.digitalRead(this.pin)){
            this.currentState = !this.currentState;
        }
    }

}
