import TI.BoeBot;
import TI.Timer;

import java.awt.*;

public class Led implements Updatable {

    private int pin;
    private Color color;
    private Timer timer1;

    private boolean state;


    public Led(int pin, Color color) {
        this.pin = pin;
        this.color = color;
        BoeBot.rgbSet(this.pin, this.color);
    }

    public Led(int pin, Color color, int time){
        this.pin = pin;
        this.color = color;
        this.timer1 = new Timer(time);
        BoeBot.rgbSet(this.pin, this.color);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTime(int time) {
        this.timer1.setInterval(time);
    }

    public void toggle(){
        this.state =! this.state;

    }

    public void ledOn(){
        BoeBot.rgbShow();
    }

    @Override
    public void Update() {

    }

}
