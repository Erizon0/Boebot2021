import TI.Servo;
import TI.Timer;

public class ServoMotor implements Updatable {

    private static final double INCREMENTPERCENT = 1.05; //By what percentage will the robot ramp up
    static final double NEUTRAL = 1500; //What number makes the servo stop driving
    private final double INCREMENTFLOOR = 0.01;
    private static final double INCREMENTCEIL = 1.0;

    //Temporary var for testing without boebot
    //private int tempspeed;

    private int currentSpeed;
    private int targetSpeed;

    //TODO: There is a better way to do this
    private int direction; //Is either 1 or -1
    private Servo servo;

    private Timer timer = new Timer(100);

    private double incrementNumber;


    public ServoMotor(int pin, int direction) {
        incrementNumber = INCREMENTFLOOR;
        this.direction = direction;

        this.servo = new Servo(pin);
        this.servo.start();
    }

    public void goToSpeed(int speed) {
        incrementNumber = INCREMENTFLOOR;
        targetSpeed = speed + 1500;
    }

    public void stop() {
        incrementNumber = INCREMENTFLOOR;
        targetSpeed = 1500;
        servo.update(1500);
    }

    //TODO: This method of ramping up could be a lot better (the way this works with incrementNumber), but I can't think of a better way right now.
    @Override
    public void Update() {

        currentSpeed = servo.getPulseWidth();
        System.out.println(currentSpeed);
        if(currentSpeed > 1600 || currentSpeed < 1400){
            return;
        }

        if (targetSpeed != currentSpeed && timer.timeout()) {
            timer.mark();
            if (incrementNumber < INCREMENTCEIL) {
                //Print out the increment difference
                //System.out.println(incrementNumber * INCREMENTPERCENT - incrementNumber);
                incrementNumber *= INCREMENTPERCENT;
            }

            int difference = targetSpeed - currentSpeed;
            double incrementSpeed =  difference * incrementNumber;
            int newSpeed = this.direction * (currentSpeed + (int)Math.ceil(incrementSpeed));
            this.currentSpeed = newSpeed;
            servo.update(newSpeed);
            //System.out.println("Updated speed to: " + newSpeed);
        }

    }

}
