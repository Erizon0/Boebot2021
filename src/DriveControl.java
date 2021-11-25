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

    Buzzer buzzer = new Buzzer(10, 30, 250);
    Led rightLed = new Led(0, Color.white);
    Led leftLed = new Led(1, Color.white);
    Led backLed = new Led(2, Color.white);
    Led backLed1 = new Led(3, Color.white);

    public DriveControl(ServoMotor leftServoMotor, ServoMotor rightServoMotor, Whisker leftWhisker, Whisker rightWhisker) {
        this.leftServoMotor = leftServoMotor;
        this.rightServoMotor = rightServoMotor;
        this.leftWhisker = leftWhisker;
        this.rightWhisker = rightWhisker;
        BoeBot.setMode(0, PinMode.Input);
    }

    @Override
    public void Update() {
        if (button.isPressed()) {
            buttonTimer.mark();
            leftServoMotor.stop();
            rightServoMotor.stop();
            return;
        } else {
            if (leftWhisker.isPressed() || backUpLeft) {

                if (!backUpLeft) {
                    leftServoMotor.goToSpeed(-100);
                    rightServoMotor.goToSpeed(-100);
                    backLed.toggle();
                    backLed1.toggle();
                    buzzer.toggle();
                    backUpLeft = true;
                    backUpTimer.mark();
                } else {
                    if (backUpTimer.timeout()) {
                        leftServoMotor.goToSpeed(100);
                        rightServoMotor.goToSpeed(-100);
                        backLed.toggle();
                        backLed1.toggle();
                        buzzer.toggle();
                        leftLed.toggle();
                        turnTimer.mark();
                    }
                    if (turnTimer.timeout()) {
                        leftServoMotor.goToSpeed(100);
                        rightServoMotor.goToSpeed(100);
                        leftLed.toggle();
                        backUpLeft = false;
                    }
                }

            } else if (rightWhisker.isPressed() || backUpRight) {

                if (!backUpRight) {
                    leftServoMotor.goToSpeed(-100);
                    rightServoMotor.goToSpeed(-100);
                    backLed.toggle();
                    backLed1.toggle();
                    buzzer.toggle();
                    backUpRight = true;
                    backUpTimer.mark();
                } else {
                    if (backUpTimer.timeout()) {
                        leftServoMotor.goToSpeed(-100);
                        rightServoMotor.goToSpeed(100);
                        backLed.toggle();
                        backLed1.toggle();
                        buzzer.toggle();
                        rightLed.toggle();
                        turnTimer.mark();
                    }
                    if (turnTimer.timeout()) {
                        leftServoMotor.goToSpeed(100);
                        rightServoMotor.goToSpeed(100);
                        rightLed.toggle();
                        backUpRight = false;
                    }
                }
            }
        }

        leftServoMotor.Update();
        rightServoMotor.Update();

    }
}


