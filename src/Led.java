import TI.BoeBot;

public class Led implements Updatable {

    private int pin;

    public Led(int pin) {
        this.pin = pin;
    }

    public void blink() {
        //Add LED blink code
        BoeBot.rgbSet(this.pin, 255, 255, 255);
        BoeBot.rgbShow();
    }

    @Override
    public void Update() {

    }

}
