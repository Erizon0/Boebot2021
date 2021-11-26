package Hardware;

import TI.BoeBot;
import TI.Timer;

import java.awt.*;

public class Led implements Update{
    private int pin;
    private Color color;
    private Timer timer1;

    private int state = 0;

    //TODO: Check if this works
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
        switch (this.state){
            case 0:
                turnOn();
                break;
            case 1:
                turnOff();
                break;
        }
    }

    public void turnOn(){
        BoeBot.rgbSet(this.pin,this.color);
        BoeBot.rgbShow();
        this.state = 1;
    }

    public void turnOff(){
        BoeBot.rgbSet(this.pin, Color.BLACK);
        BoeBot.rgbShow();
        this.state = 0;

    }

    @Override
    public void update() {
        if(this.timer1 == null)
            return;
        if (this.timer1.timeout()){
            toggle();
            this.timer1.mark();
        }
    }
}
