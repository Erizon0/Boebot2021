import TI.PWM;
import TI.Timer;

public class Buzzer implements Updatable {

    private int pin;
    private int frequency;
    private boolean state;
    private Timer tBeep;;

    private PWM pwm;

    //create new buzzer
    public Buzzer(int pin, int frequency) {
        this.pin = pin;
        this.frequency = frequency;
        this.pwm = new PWM(this.pin, this.frequency);
    }

    //create new buzzer with interval time
    public Buzzer(int pin, int frequency, int interval){
        this.pin = pin;
        this.frequency = frequency;
        this.pwm = new PWM(this.pin, this.frequency);
        tBeep = new Timer(interval);
    }

    //manualy start buzzer
    public void startBuzzer() { ;
        this.pwm.start();
    }

    //manualy stop buzzer
    public void stopBuzzer() {
        this.pwm.stop();
    }

    //switches state of the buzzer
    public void toggle(){
        this.state =! this.state;
        if(this.state = true)
            startBuzzer();
        if(this.state = false)
            stopBuzzer();
    }

    //update method for making the buzzer beep with interval time
    @Override
    public void Update() {
        if(this.tBeep == null)
            return;
        if (this.tBeep.timeout()){
            toggle();
            this.tBeep.mark();
        }
    }

}
