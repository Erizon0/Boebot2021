import TI.BoeBot;
import TI.PWM;
import TI.Timer;

public class Buzzer implements Updatable {

    private int pin;
    private int frequency;
    private Timer tBeep;
    private Timer tBeep2;

    private PWM pwm;


    public Buzzer(int pin, int frequency) {
        this.pin = pin;
        this.frequency = frequency;
        this.pwm = new PWM(this.pin, this.frequency);
       //volgens mij moet je hier nog "BoeBot.freqOut();" gebruiken
    }

    public void continuousBeep (int frequency) {
        this.pwm.update(frequency);
        this.pwm.start();
    }

    public void beep(int frequency, int interval){
        this.tBeep = new Timer(interval);
        this.tBeep2 = new Timer(interval);
        this.pwm.update(frequency);
        this.pwm.start();
        if (this.tBeep.timeout()) {
            this.pwm.stop();
        }
        if (this.tBeep2.timeout()){
            this.pwm.start();
        }
    }

    //May be a handy method, but it depends how we code beep()
    public void stopBeep() {
        this.pwm.stop();
    }

    //Don't know what to do with this yet
    @Override
    public void Update() {

    }

}
