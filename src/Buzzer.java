import TI.BoeBot;
import TI.PWM;
import TI.Timer;

public class Buzzer implements Updatable {

    private int pin;
    private int frequency;
    private boolean state;
    private Timer tBeep;;

    private PWM pwm;

    //volgens mij moet je hier nog "BoeBot.freqOut();" gebruiken
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

    public void startBeep () { ;
        this.pwm.start();
    }

    public void stopBeep() {
        this.pwm.stop();
    }

    public void toggle(){
        this.state =! this.state;
        if(this.state = true)
            startBeep();
        if(this.state = false)
            stopBeep();
    }

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
