package Hardware;

import Interface.Update;
import TI.PWM;
import TI.Timer;

/**
 * A hardware buzzer
 */
public class Buzzer implements Update {

    private int pin;
    private int frequency;
    private int currentState;
    private Timer beepTimer;

    private PWM pwm;

    private boolean isBuzzing;

    /** Constructor for a buzzer which beeps constantly
     * @param pin Which pin the buzzer is connected to
     * @param frequency Which frequency the buzzer needs to beep with
     */
    public Buzzer(int pin, int frequency) {
        this.pin = pin;
        this.frequency = frequency;
        this.pwm = new PWM(this.pin, this.frequency);
    }

    /** Constructor for a buzzer which beeps constantly
     * @param pin Which pin the buzzer is connected to
     * @param frequency Which frequency the buzzer needs to beep with
     * @param interval With which interval the buzzer should beep
     */
    public Buzzer(int pin, int frequency, int interval){
        this.pin = pin;
        this.frequency = frequency;
        this.pwm = new PWM(this.pin, this.frequency);
        this.beepTimer = new Timer(interval);
        this.isBuzzing = false;
    }

    /**
     * Start the beeping of the buzzer
     */
    public void startBeep() {
        this.pwm.start();
        this.currentState = 1;
    }

    /**
     * Stop the beeping of the buzzer
     */
    public void stopBeep() {
        this.pwm.stop();
        this.currentState = 0;
    }

    /** Set the buzzer
     * @param bool Signifies if the buzzer should beep
     */
    public void setBuzzing(boolean bool){
        this.isBuzzing = bool;
        if (bool) {
            startBeep();
        } else {
            stopBeep();
        }
    }

    /**
     * Toggle the state of the buzzer
     */
    public void toggle(){
        switch (this.currentState){
            case 0:
                startBeep();
                break;
            case 1:
                stopBeep();
                break;
        }
    }

    @Override
    public void update() {
        //System.out.println(this.isBuzzing);
        if (this.beepTimer.timeout() && this.isBuzzing){
            toggle();
            this.beepTimer.mark();
        }
    }

}
