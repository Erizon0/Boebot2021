package Hardware;

import Interface.ServoCallback;
import Interface.Update;
import TI.Timer;
import Tools.Direction;
import Tools.ToggleTimer;

/**
 * The class that controls the two servo's
 */
public class ServoControl implements Update {

    private ServoMotor leftServo;
    private ServoMotor rightServo;

    //Timers for timing certain actions
    private ToggleTimer turningTimer = new ToggleTimer(1000);
    private Timer drivingTimer = new Timer(1000);

    private ServoCallback callback;

    private boolean isTimeTurning;

    //Boolean that says if the robot is driving a certain time
    private boolean isTimedDriving = false;

    /** Constructor for ServoControl
     * @param leftServo The servo on the left side
     * @param rightServo The servo on the right side
     */
    public ServoControl(ServoMotor leftServo, ServoMotor rightServo) {
        this.leftServo = leftServo;
        this.rightServo = rightServo;
        this.leftServo.stop();
        this.rightServo.stop();
    }

    public void setCallback(ServoCallback servoCallback) {
        this.callback = servoCallback;
    }

    /** Make the bot turn a certain direction and a certain speed
     * @param direction What direction the bot should turn
     * @param speed What speed the bot should turn with
     */
    public void turn(Direction direction, int speed) {
       this.instantStop();

        if (direction == Direction.LEFT) {
            this.leftServo.goToSpeed(-speed);
            this.rightServo.goToSpeed(speed);
            this.callback.turnLeftOn();
        } else if (direction == Direction.RIGHT) {
            this.leftServo.goToSpeed(speed);
            this.rightServo.goToSpeed(-speed);
            this.callback.turnRightOn();
        }

    }

    /** Turn the bot for a period of time
     * @param time The time the bot should turn
     * @param direction What direction the bot should turn
     */
    public void timeTurn(int time, Direction direction) {
        this.instantStop();

        this.turningTimer = new ToggleTimer(time);
        this.turningTimer.setActive(true);
        this.isTimeTurning = true;

        if (direction == Direction.LEFT) {
            this.leftServo.goToSpeed(-1);
            this.rightServo.goToSpeed(1);
            this.callback.turnLeftOn();
        } else if (direction == Direction.RIGHT) {
            this.leftServo.goToSpeed(1);
            this.rightServo.goToSpeed(-1);
            this.callback.turnRightOn();
        }

    }

    /** Make the bot ramp up to the given speed
     * @param speed The speed the bot should drive with
     */
    public void goToSpeed(int speed) {
        this.leftServo.goToSpeed(speed);
        this.rightServo.goToSpeed(speed);
        this.callback.setDriving(true);
        this.callback.resetLed();
    }

    /** Make the bot drive for a certain time with a given speed
     * @param speed The speed the bot should drive with
     * @param time The length of time the bot should drive with
     */
    public void timedDrive(int speed, int time) {
        this.instantStop();
        this.leftServo.goToSpeed(speed);
        this.rightServo.goToSpeed(speed);
        this.drivingTimer.setInterval(time);
        this.drivingTimer.mark();
        this.isTimedDriving = true;
    }

    /**
     * Make the bot stop slowly
     */
    public void stop(){
        this.leftServo.goToSpeed(0);
        this.rightServo.goToSpeed(0);
        this.callback.resetLed();
    }

    /**
     * Make the bot stop instantly
     */
    public void instantStop() {
        this.leftServo.stop();
        this.rightServo.stop();
        this.callback.resetLed();
    }

    @Override
    public void update() {

        //TODO: The bot will take more time than the timer to slow down gradually, need to find a way to calculate extra time, but for now good enough
        //Check if drivingTimer has run out
        if (this.drivingTimer.timeout() && this.isTimedDriving) {
            this.instantStop();
            this.isTimedDriving = false;
        } else if (this.turningTimer.timeout() && this.isTimeTurning) {

            this.turningTimer.setActive(false);
            this.isTimeTurning = false;
            this.leftServo.stop();
            this.rightServo.stop();
            this.callback.turnRightOff();
            this.callback.turnLeftOff();
            this.callback.resetLed();
        }

    }
    
    public void lineServo(){

    }
}
