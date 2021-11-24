import TI.BoeBot;
import TI.PWM;
import TI.Timer;

public class Buzzer implements Updatable {

    private int pin;
    private int frequency;
    private boolean state;
    private Timer tBeep;;

    private PWM pwm;


    public Buzzer(int pin, int frequency) {
        this.pin = pin;
        this.frequency = frequency;
        this.pwm = new PWM(this.pin, this.frequency);
       //volgens mij moet je hier nog "BoeBot.freqOut();" gebruiken
    }

    public void startContinuousBeep () { ;
        this.pwm.start();
    }

    public void startBeeping(int interval){
        this.tBeep = new Timer(interval);
        this.pwm.start();
    }

    public void toggle(){
        this.state =! this.state;
    }

    //May be a handy method, but it depends how we code beep()
    public void stopBeep() {
        this.pwm.stop();
    }

    //Don't know what to do with this yet
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
