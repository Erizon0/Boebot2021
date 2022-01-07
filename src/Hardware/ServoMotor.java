package Hardware;

import TI.*;

/**
 * A hardware servo
 */
public class ServoMotor {

    private int targetGear;
    private int currentGear;

    private boolean linefollowmode = false;
    //TODO: There is a better way to do the directions, but good enough for now
    private int direction; //Is either 1 or -1
    private Servo servo;

    private Timer updateTimer = new Timer(200);

    /**
     * Construct a ServoMotor
     *
     * @param pin       What pin the servo is on
     * @param direction What direction the servo is facing
     */
    public ServoMotor(int pin, int direction) {
        this.direction = direction;
        this.servo = new Servo(pin);
        this.servo.start();

        this.currentGear = 0;
        this.targetGear = 0;
    }

    /**
     * Get the current speed of the servo
     *
     * @return The speed the servo is going
     */
    public int getSpeed() {
        return this.currentGear;
    }

    /**
     * Make the bot ramp up to the given speed
     *
     * @param gear The gear the servo should ramp up to
     */
    public void goToSpeed(int gear) {
        this.targetGear = this.direction * gear;
    }

    /**
     * Instantly stop the servo's
     */
    public void stop() {
        this.targetGear = 0;
        this.currentGear = 0;
        this.servo.update(1500);
        this.servo.start();
    }

    public void update() {
        if (!linefollowmode) {
            //Check if the current gear is the same as the target gear
            if (this.targetGear != this.currentGear) {
                //Check if updateTimer has run out and the servo's can go a gear higher
                if (updateTimer.timeout()) {

                    //If the target gear is bigger than the current gear go a gear higher, or else go lower. If it is equal do nothing
                    if (this.targetGear > this.currentGear) {
                        currentGear++;
                    } else if (this.targetGear < this.currentGear) {
                        currentGear--;
                    }
                    this.updateTimer.mark();
                }
            }
            //Update the servo's to the right speed
            this.servo.update(this.currentGear * 20 + 1500);
        }
    }

    public void instantUpdate(int speed) {
        if (this.linefollowmode) {

            this.servo.update(speed);
        }
    }

    public void setLinefollowmode(boolean state) {
        this.linefollowmode = state;
    }
}
