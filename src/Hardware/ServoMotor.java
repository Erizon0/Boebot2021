package Hardware;

import TI.Servo;
import TI.Timer;

public class ServoMotor implements Update {

    private int targetGear;
    private int currentGear;

    //TODO: There is a better way to do the directions, but good enough for now
    private int direction; //Is either 1 or -1
    private Servo servo;

    private Timer timer = new Timer(200);

    public ServoMotor(int pin, int direction) {
        this.direction = direction;
        this.servo = new Servo(pin);
        this.servo.start();

        currentGear = 0;
        targetGear = 0;
    }

    public int getSpeed() {
        return this.currentGear;
    }

    public void goToSpeed(int gear) {
        this.targetGear = this.direction * gear;

    }

    public void stop() {
        this.targetGear = 0;
        this.currentGear = 0;
    }


    //*uses gears instead of an overly complicated exponential curve*
    @Override
    public void update() {

        if (targetGear != currentGear) {
            if (timer.timeout()) {
                if (targetGear > currentGear) {
                    currentGear++;
                } else if (targetGear < currentGear) {
                    currentGear--;
                }
                timer.mark();
            }
        }
        servo.update(currentGear * 20 + 1500);
    }
}
