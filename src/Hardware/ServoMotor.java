package Hardware;

import Interface.Update;
import TI.*;

public class ServoMotor {

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

    //Get the current speed of the servo
    public int getSpeed() {
        return this.currentGear;
    }

    //Set the target speed and ramp up slowly
    public void goToSpeed(int gear) {
        this.targetGear = this.direction * gear;
    }

    //Instantly stop the servo's
    public void stop() {
        this.targetGear = 0;
        this.currentGear = 0;
    }

    //*uses gears instead of an overly complicated exponential curve*
    public void update() {

        //Check if the current gear is the same as the target gear
        if (targetGear != currentGear) {
            //Check if timer has run out and the servo's can go a gear higher
            if (timer.timeout()) {

                //If the target gear is bigger than the current gear go a gear higher, or else go lower. If it is equal do nothing
                if (targetGear > currentGear) {
                    currentGear++;
                } else if (targetGear < currentGear) {
                    currentGear--;
                }
                timer.mark();
            }
        }
        //Update the servo's to the right speed
        servo.update(currentGear * 20 + 1500);
    }
}
