package Hardware;

import Interface.Update;
import TI.BoeBot;
import TI.Timer;
import Tools.LedState;
import Tools.ToggleTimer;

import java.awt.*;

/**
 * A Led
 */
public class Led implements Update {
    private int pin;
    private Color color;
    private ToggleTimer blinkTimer;
    private boolean blinker = false;
    private LedState blinkState;

    /** Constructor for led with no timer
     * @param pin What pin the LED is on
     * @param color What color the LED should be
     */
    public Led(int pin, Color color) {
        this.pin = pin;
        this.color = color;
        this.blinkTimer = new ToggleTimer(1);
        this.blinkState = LedState.OFF;
        BoeBot.rgbSet(this.pin, this.color);
    }

    /** Constructor for led with timer
     * @param pin What pin de LED is on
     * @param color What color the LED should be
     * @param time With which interval the LED should blink
     */
    public Led(int pin, Color color, int time){
        this.pin = pin;
        this.color = color;
        this.blinkTimer = new ToggleTimer(time);
        this.blinkState = LedState.OFF;
        BoeBot.rgbSet(this.pin, this.color);
    }

    /** Set the color of the LED
     * @param color Which color the LEd should be
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /** Set the interval with which the LED should beep
     * @param time With which interval the LED should blink
     */
    public void setInterval(int time) {
        this.blinkTimer.setInterval(time);
    }

    /**
     * Toggle the state of the LED
     */
    public void toggle(){

        if (this.blinkState == LedState.BLINKOFF) {
            turnBlinkOn();
        } else if (this.blinkState == LedState.BLINKON) {
            turnBlinkOff();
        }

    }

    /**
     * Make the LED turn on and blink
     */
    public void turnBlinkOn(){
        this.blinkState = LedState.BLINKON;
        blinkTimer.setActive(true);
        if (this.blinkTimer.timeout()){
            BoeBot.rgbSet(this.pin,Color.black);
            BoeBot.rgbShow();
            blinkTimer.mark();
        } else {
            BoeBot.rgbSet(this.pin,this.color);
            BoeBot.rgbShow();
            blinkTimer.mark();
        }

    }

    /**
     * Just turn the LED on
     */
    public void turnOn() {
        this.blinkState = LedState.ON;
        this.blinkTimer.setActive(false);
        BoeBot.rgbSet(this.pin, this.color);
        BoeBot.rgbShow();
    }

    /**
     * Make the LED turn off and blink
     */
    public void turnBlinkOff(){
        this.blinkState = LedState.BLINKOFF;
        BoeBot.rgbSet(this.pin, Color.BLACK);
        BoeBot.rgbShow();
    }

    /**
     * Just turn the LED off
     */
    public void turnOff() {
        this.blinkState = LedState.OFF;
        this.blinkTimer.setActive(false);
        BoeBot.rgbSet(this.pin, Color.BLACK);
        BoeBot.rgbShow();
    }

    @Override
    public void update() {
        if (this.blinkTimer.timeout() && (this.blinkState == LedState.BLINKOFF || this.blinkState == LedState.BLINKON)){
            toggle();
            this.blinkTimer.mark();
        } else if (this.blinkState != LedState.BLINKOFF && this.blinkState != LedState.BLINKON) {
            //Code for when the Led is not blinking
        }
    }
}