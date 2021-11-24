import TI.BoeBot;
import TI.Timer;

import java.awt.*;

public class Led implements Updatable {

    private int pin;
    private Color color;
    private Timer timer1;
    private boolean state;

    //create new led
    public Led(int pin, Color color) {
        this.pin = pin;
        this.color = color;
        BoeBot.rgbSet(this.pin, this.color);
    }

    //create new led with interval time
    public Led(int pin, Color color, int time){
        this.pin = pin;
        this.color = color;
        this.timer1 = new Timer(time);
        BoeBot.rgbSet(this.pin, this.color);
    }

    //set color of led
    public void setColor(Color color) {
        this.color = color;
    }

    //set new time for blinking interval
    public void setTime(int time) {
        this.timer1.setInterval(time);
    }

    //switches current state of state
    public void toggle(){
        this.state =! this.state;
        if (this.state = true)
            turnOn();
        if (this.state = false)
            turnOff();
    }

    //manualy turn on led
    public void turnOn(){
        BoeBot.rgbShow();
    }

    //manualy turn off led
    public void turnOff(){
        BoeBot.rgbSet(this.pin, 0,0,0);
        BoeBot.rgbShow();
    }

    //update method for making the led blink on inputted time
    @Override
    public void Update() {
        if(this.timer1 == null)
            return;
        if (this.timer1.timeout()){
            toggle();
            this.timer1.mark();
        }
    }

}
