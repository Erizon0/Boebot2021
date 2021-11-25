import TI.Servo;
import TI.Timer;

public class ServoMotor implements Updatable {

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

    public void goToSpeed(int gear) {
        this.targetGear = this.direction * gear;
        timer.mark();
    }

    public void stop() {
        this.targetGear = 0;
        this.currentGear = 0;
    }

    //Fuck this
    //*uses gears instead of an overly complicated exponential curve*
    @Override
    public void Update() {

        if (targetGear != currentGear) {
            if (timer.timeout()) {
                if (targetGear > currentGear) {
                    currentGear++;
                } else if (targetGear < currentGear) {
                    currentGear--;
                }
                timer.timeout();
            }
        }

        switch (currentGear) {
            case -5:
                servo.update(1400);
                break;
            case -4:
                servo.update(1420);
                break;
            case -3:
                servo.update(1440);
                break;
            case -2:
                servo.update(1460);
                break;
            case -1:
                servo.update(1480);
                break;
            case 0:
                servo.update(1500);
                break;
            case 1:
                servo.update(1520);
                break;
            case 2:
                servo.update(1540);
                break;
            case 3:
                servo.update(1560);
                break;
            case 4:
                servo.update(1580);
                break;
            case 5:
                servo.update(1600);
                break;
        }

    }

}
