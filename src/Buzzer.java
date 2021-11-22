import TI.BoeBot;
import TI.PWM;

public class Buzzer implements Updatable {

    private int pin;
    private int frequency;

    private PWM pwm;

    public Buzzer(int pin, int frequency) {
        this.pin = pin;
        this.frequency = frequency;
        this.pwm = new PWM(this.pin, this.frequency);
       //volgens mij moet je hier nog "BoeBot.freqOut();" gebruiken
    }

    public void beep(int frequency) {
        this.pwm.update(frequency);
        this.pwm.start();
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
