import TI.BoeBot;
import TI.PinMode;
import TI.Timer;

import java.awt.*;

public class DriveControl implements Updatable {

    private ServoMotor leftServoMotor;
    private ServoMotor rightServoMotor;

    private Whisker leftWhisker;
    private Whisker rightWhisker;

    private boolean backUpLeft = false;
    private boolean backUpRight = false;

    private Timer turnTimer = new Timer(500);
    private Timer backUpTimer = new Timer(500);
    private Timer buttonTimer = new Timer(5000);

    private Button button = new Button(0);

    //TODO: Don't know if buzzer works
    Buzzer buzzer = new Buzzer(10, 150, 500);

    //TODO: Don't know which pin is which led
    Led rightLed = new Led(0, Color.red, 500);
    Led leftLed = new Led(5, Color.white, 500);
    Led backLed = new Led(2, Color.white,500);
    Led backLed1 = new Led(3, Color.red, 500);

    public DriveControl(ServoMotor leftServoMotor, ServoMotor rightServoMotor, Whisker leftWhisker, Whisker rightWhisker) {
        this.leftServoMotor = leftServoMotor;
        this.rightServoMotor = rightServoMotor;
        this.leftWhisker = leftWhisker;
        this.rightWhisker = rightWhisker;
        BoeBot.setMode(0, PinMode.Input);

        leftServoMotor.goToSpeed(4);
        leftServoMotor.goToSpeed(4);

    }

    //Yes we know this is bad code, yes we will fix it later, for now it should work. With an emphasis on should.
    @Override
    public void Update() {
        if (button.isPressed()) {
            buttonTimer.mark();
            leftServoMotor.stop();
            rightServoMotor.stop();
            leftServoMotor.Update();
            rightServoMotor.Update();
            rightLed.turnOn();
            leftLed.turnOn();
            backLed.turnOn();
            backLed1.turnOn();
            return;
        } else {
            if (leftWhisker.isPressed() || backUpLeft) {

                if (!backUpLeft) {
                    leftServoMotor.goToSpeed(-2);
                    rightServoMotor.goToSpeed(-2);
                    backLed.Update();
                    backLed1.Update();
                    buzzer.Update();
                    backUpLeft = true;
                    backUpTimer.mark();
                } else {

                    if (backUpTimer.timeout()) {
                        leftServoMotor.goToSpeed(2);
                        rightServoMotor.goToSpeed(-2);
                        backLed.Update();
                        backLed1.Update();
                        buzzer.Update();
                        leftLed.Update();
                        turnTimer.mark();
                    }
                    if (turnTimer.timeout()) {
                        leftServoMotor.goToSpeed(4);
                        rightServoMotor.goToSpeed(4);
                        leftLed.Update();
                        backUpLeft = false;
                    }

                }

            } else if (rightWhisker.isPressed() || backUpRight) {

                if (!backUpRight) {
                    leftServoMotor.goToSpeed(-2);
                    rightServoMotor.goToSpeed(-2);
                    backLed.Update();
                    backLed1.Update();
                    buzzer.Update();
                    backUpRight = true;
                    backUpTimer.mark();
                } else {
                    if (backUpTimer.timeout()) {
                        leftServoMotor.goToSpeed(-2);
                        rightServoMotor.goToSpeed(2);
                        backLed.Update();
                        backLed1.Update();
                        buzzer.Update();
                        rightLed.Update();
                        turnTimer.mark();
                    }
                    if (turnTimer.timeout()) {
                        leftServoMotor.goToSpeed(4);
                        rightServoMotor.goToSpeed(4);
                        rightLed.Update();
                        backUpRight = false;
                    }
                }
            }
        }

        leftServoMotor.Update();
        rightServoMotor.Update();

    }
}


