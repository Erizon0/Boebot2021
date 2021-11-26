package Hardware;

import Interface.Update;
import TI.BoeBot;
import TI.PWM;
import TI.Timer;

public class Buzzer implements Update {

    private int pin;
    private int frequency;
    private int state;
    private int interval;
    private Timer tBeep;;

    private PWM pwm;

    public Buzzer(int pin, int frequency) {
        this.pin = pin;
        this.frequency = frequency;
        this.pwm = new PWM(this.pin, this.frequency);
    }

//    public Buzzer(int pin, int frequency, int interval){
//        this.pin = pin;
//        this.frequency = frequency;
//        this.pwm = new PWM(this.pin, this.frequency);
//        this.interval = interval;
//        tBeep = new Timer(interval);
//    }

    public Buzzer(int pin, int frequency, int interval){
        BoeBot.freqOut(pin,frequency,interval);

    }

    public int getFrequency() {
        return frequency;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void settInterval(int interval) {
        this.tBeep.setInterval(interval);
        this.tBeep.mark();
    }

    public void startBeep () {
        this.pwm.start();
        this.state = 1;
    }

    public void stopBeep() {
        this.pwm.stop();
        this.state = 0;
    }

    public void toggle(){
        switch (this.state){
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
        if(this.tBeep == null)
            return;
        if (this.tBeep.timeout()){
            toggle();
            this.tBeep.mark();
        }
    }

}
