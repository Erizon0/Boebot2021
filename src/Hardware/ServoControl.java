package Hardware;

import Interface.Update;
import TI.Timer;

public class ServoControl implements Update {

    private ServoMotor leftServo;
    private ServoMotor rightServo;

    //Timers for timing certain actions
    private Timer turnTimer = new Timer(100);
    private Timer driveTimer = new Timer(100);

    //Booleans that say if the robot is performing a "Back Turn"
    private boolean performingBLTurn = false;
    private boolean performingBRTurn = false;

    public ServoControl(ServoMotor leftServo, ServoMotor rightServo) {
        this.leftServo = leftServo;
        this.rightServo = rightServo;
    }

    //TODO: turnRight and turnLeft have duplicate code
    //Turn right for a certain time
    public void turnRight(int time) {
        this.turnTimer = new Timer(time);
        this.turnTimer.mark();
        this.leftServo.goToSpeed(1);
        this.rightServo.goToSpeed(-1);
    }

    //Turn left for a certain time
    public void turnLeft(int time) {
        this.turnTimer = new Timer(time);
        this.turnTimer.mark();
        this.leftServo.goToSpeed(-1);
        this.rightServo.goToSpeed(1);
    }

    //Slowly ramp up to the designated speed
    public void goToSpeed(int speed) {
        this.leftServo.goToSpeed(speed);
        this.rightServo.goToSpeed(speed);
    }

    //Drive for a certain time with given speed
    public void timedDrive(int speed, int time) {
        this.leftServo.goToSpeed(speed);
        this.rightServo.goToSpeed(speed);
        this.driveTimer.setInterval(time);
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
            timedDrive(-1, 400);
            performingBLTurn = true;
        } else {
            turnLeft(200);
            performingBLTurn = false;
        }
    }

    //Drive backwards, then turn to the right
    public void turnBackRight() {
        if (!performingBLTurn) {
            timedDrive(-1, 400);
            performingBRTurn = true;
        } else {
            turnRight(200);
            performingBRTurn = false;
        }
    }

    @Override
    public void update() {

        //TODO: Check if when a timer times out it says timeout once or multiple times (for now the setInterval)
        //TODO: The bot will take more time than the timer to slow down gradually, need to find a way to calculate extra time, but for now good enough
        //Check if turnTimer has run out and make the robot stop
        if (turnTimer.timeout()) {
            turnTimer.setInterval(100);
            this.leftServo.stop();
            this.leftServo.stop();
        }

        //Check if driveTimer has run out
        if (driveTimer.timeout()) {
            driveTimer.setInterval(100);

            //Check if the robot was performing a backLeft turn or a backRight turn
            if (performingBLTurn) {
                turnBackLeft();
            } else if (performingBRTurn) {
                turnBackRight();
            }
        }

        //Update the servo's so they will check if they are running at the correct speed and correct if necessary
        leftServo.update();
        rightServo.update();

    }
}
