package Hardware;

import TI.PWM;
import TI.Timer;

public class Buzzer implements Update {

    private int pin;
    private int frequency;
    private int state;
    private Timer tBeep;;

    private PWM pwm;

    public Buzzer(int pin, int frequency) {
        this.pin = pin;
        this.frequency = frequency;
        this.pwm = new PWM(this.pin, this.frequency);
    }

    public Buzzer(int pin, int frequency, int interval){
        this.pin = pin;
        this.frequency = frequency;
        this.pwm = new PWM(this.pin, this.frequency);
        tBeep = new Timer(interval);
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
