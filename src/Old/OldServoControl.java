package Old;

import Hardware.ServoMotor;
import Interface.DriveState;
import Interface.ServoCallback;
import Interface.Update;
import TI.Servo;
import TI.Timer;

/**
 * OLD, DON'T USE
 */
public class OldServoControl implements Update {

    private ServoMotor leftServo;
    private ServoMotor rightServo;

    //Timers for timing certain actions
    private Timer turnTimer = new Timer(1000);
    private Timer driveTimer = new Timer(1000);

    private ServoCallback callback;

    private boolean turning;

    //Booleans that say if the robot is performing a "Back Turn"
    private boolean DriveTime = false;
    private boolean performingBLTurn = false;
    private boolean performingBRTurn = false;

    public OldServoControl(ServoMotor leftServo, ServoMotor rightServo) {
        this.leftServo = leftServo;
        this.rightServo = rightServo;
    }

    public void setCallback(ServoCallback callback) {
        this.callback = callback;
    }

    //TODO: turnRight and turnLeft have duplicate code
    //Turn right for a certain time
    public void turnRight(int time) {
        this.instantStop();
        this.turnTimer = new Timer(time);
        this.turnTimer.mark();
        this.turning = true;
        this.leftServo.goToSpeed(1);
        this.rightServo.goToSpeed(-1);
        callback.turnRightOn();
    }

    //Turn left for a certain time
    public void turnLeft(int time) {
        this.instantStop();
        this.turnTimer = new Timer(time);
        this.turnTimer.mark();
        this.turning = true;
        this.leftServo.goToSpeed(-1);
        this.rightServo.goToSpeed(1);
        callback.turnLeftOn();
    }

    //Slowly ramp up to the designated speed
    public void goToSpeed(int speed) {
        this.leftServo.goToSpeed(speed);
        this.rightServo.goToSpeed(speed);
        callback.setDriving(true);
    }

    //Drive for a certain time with given speed
    public void timedDrive(int speed, int time) {
        //System.out.println("Driving backwards");
        this.instantStop();
        this.leftServo.goToSpeed(speed);
        this.rightServo.goToSpeed(speed);
        this.driveTimer.setInterval(time);
        this.driveTimer.mark();
        this.DriveTime = true;
    }

    //Slowly stop the servo's
    public void stop(){
        this.leftServo.goToSpeed(0);
        this.rightServo.goToSpeed(0);
    }

    //Instantly stop the servo's
    public void instantStop() {
        this.leftServo.stop();
        this.rightServo.stop();
    }

    //TODO: Same thing as turnLeft and TurnRight
    //Drive backwards, then turn to the left
    public void turnBackLeft() {
        if (!performingBLTurn) {
            timedDrive(-2, 1500);
            callback.buzzerToggle(true);
            performingBLTurn = true;
        } else {
            callback.buzzerToggle(false);
            turnLeft(1000);
            performingBLTurn = false;
        }
    }

    //Drive backwards, then turn to the right
    public void turnBackRight() {
        if (!performingBRTurn) {
            timedDrive(-2, 1500);
            callback.buzzerToggle(true);
            performingBRTurn = true;
        } else {
            callback.buzzerToggle(false);
            turnRight(1000);
            performingBRTurn = false;

        }
    }

    @Override
    public void update() {

        //TODO: Check if when a timer times out it says timeout once or multiple times (for now the setInterval)
        //TODO: The bot will take more time than the timer to slow down gradually, need to find a way to calculate extra time, but for now good enough

        if(turnTimer.timeout() && this.turning) {
            this.leftServo.stop();
            this.rightServo.stop();
            callback.turnRightOff();
            this.rightServo.goToSpeed(5);
            this.leftServo.goToSpeed(5);
            callback.turnLeftOff();
            this.turning = false;
            callback.resetLed();
        }

        //Check if driveTimer has run out
        if (driveTimer.timeout()) {


            if (this.DriveTime) {
                this.instantStop();
                this.DriveTime = false;

            }

            //Check if the robot was performing a backLeft turn or a backRight turn
            if (performingBLTurn) {
                turnBackLeft();
                //callback.buzzerToggle();
                //System.out.println(performingBLTurn);
            } else if (performingBRTurn) {
                turnBackRight();
                //System.out.println(performingBRTurn);
            }
            this.driveTimer.mark();
        }

        //Update the servo's so they will check if they are running at the correct speed and correct if necessary
        leftServo.update();
        rightServo.update();

    }
}
