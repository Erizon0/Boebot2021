import TI.BoeBot;

import java.awt.*;

public class Led implements Updatable {

    private int pin;

    public Led(int pin) {
        this.pin = pin;
    }

    public void blink() {
        //Add LED blink code
//        BoeBot.rgbSet(this.pin, 255, 255, 255);
        BoeBot.rgbSet(this.pin, Color.white);
        BoeBot.rgbShow();
    }

    @Override
    public void Update() {

    }

}
