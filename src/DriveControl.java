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

    private Timer turnTimer = new Timer(5000);
    private Timer stopTimer = new Timer(2000);
    private Timer backUpTimer = new Timer(1000);
    private Timer buttonTimer = new Timer(5000);

    private Button button = new Button(0);
    private int state = -1;

    //TODO: Don't know if buzzer works
    Buzzer buzzer = new Buzzer(10, 0, 500);

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

    public void backward (){
        this.rightServoMotor.goToSpeed(-1);
        this.leftServoMotor.goToSpeed(-1);
    }

    public void stop(){
        this.rightServoMotor.stop();
        this.leftServoMotor.stop();

    }

    //Yes we know this is bad code, yes we will fix it later, for now it should work. With an emphasis on should.
    @Override
    public void Update() {
        switch (this.state) {
            default:
                buttonTimer.mark();
                leftServoMotor.stop();
                rightServoMotor.stop();
                rightLed.turnOn();
                leftLed.turnOn();
                backLed.turnOn();
                backLed1.turnOn();
//                System.out.println("IK MAG NIET RIJDEN");
                if (!button.isPressed())
                    this.state = 0;
                break;

            case 0:
                leftServoMotor.goToSpeed(3);
                rightServoMotor.goToSpeed(3);
                if (rightWhisker.isPressed()) {
                    this.backUpRight = true;
                    this.state = 1;
                    this.backUpTimer.mark();
                }
                if (leftWhisker.isPressed()) {
                    this.backUpLeft = true;
                    this.state = 1;
                    this.backUpTimer.mark();
                }
                break;

            case 1:
                backward();
                if (backUpTimer.timeout())
                    this.state = 2;
                stopTimer.mark();
                break;

            case 2:
                stop();
                if (stopTimer.timeout()) {
                    System.out.println("Timer is gesprongen");
                    if (this.backUpRight) {
                        this.backUpLeft = false;
                        this.state = 3;
                    }
                    if (this.backUpLeft){
                        this.backUpRight = false;
                        this.state = 4;
                    }

                    if (!this.backUpRight && !this.backUpLeft)
                        this.state = 0;

                }
                turnTimer.mark();
                break;


            case 3:
                rightServoMotor.goToSpeed(2);
                leftServoMotor.goToSpeed(-2);
                this.backUpRight = false;
                if (turnTimer.timeout()) {
                    this.state = 2;
                    stopTimer.mark();
                }
                break;


            case 4:
                rightServoMotor.goToSpeed(-2);
                leftServoMotor.goToSpeed(2);
                this.backUpLeft = false;
                if (turnTimer.timeout()) {
                    this.state = 2;
                    stopTimer.mark();
                }
                break;
        }
        if(button.isPressed()){
            this.state = -1;
        }
        System.out.println("current state: " + this.state);
        System.out.println("current speed right: " + rightServoMotor.getSpeed());
        System.out.println("current speed left: " + leftServoMotor.getSpeed());
        leftServoMotor.Update();
        rightServoMotor.Update();


    }
}

// THIS SHIT BROKE

//        if (button.isPressed()) {
//                buttonTimer.mark();
//                leftServoMotor.stop();
//                rightServoMotor.stop();
//                leftServoMotor.Update();
//                rightServoMotor.Update();
//                rightLed.turnOn();
//                leftLed.turnOn();
//                backLed.turnOn();
//                backLed1.turnOn();
//                System.out.println("IK MAG NIET RIJDEN");
//                return;
//                } else {
//                if (leftWhisker.isPressed() || backUpLeft) {
//
//                if (!backUpLeft) {
//                leftServoMotor.goToSpeed(-2);
//                rightServoMotor.goToSpeed(-2);
//                backLed.Update();
//                backLed1.Update();
//                buzzer.Update();
//                backUpLeft = true;
//                backUpTimer.mark();
//                } else {
//
//                if (backUpTimer.timeout()) {
//                leftServoMotor.goToSpeed(2);
//                rightServoMotor.goToSpeed(-2);
//                backLed.Update();
//                backLed1.Update();
//                buzzer.Update();
//                leftLed.Update();
//                turnTimer.mark();
//                }
//                if (turnTimer.timeout()) {
//                leftServoMotor.goToSpeed(4);
//                rightServoMotor.goToSpeed(4);
//                leftLed.Update();
//                backUpLeft = false;
//                }
//
//                }
//
//                } else if (rightWhisker.isPressed() || backUpRight) {
//
//                if (!backUpRight) {
//                leftServoMotor.goToSpeed(-2);
//                rightServoMotor.goToSpeed(-2);
//                backLed.Update();
//                backLed1.Update();
//                buzzer.Update();
//                backUpRight = true;
//                backUpTimer.mark();
//                } else {
//                if (backUpTimer.timeout()) {
//                leftServoMotor.goToSpeed(-2);
//                rightServoMotor.goToSpeed(2);
//                backLed.Update();
//                backLed1.Update();
//                buzzer.Update();
//                rightLed.Update();
//                turnTimer.mark();
//                }
//                if (turnTimer.timeout()) {
//                leftServoMotor.goToSpeed(4);
//                rightServoMotor.goToSpeed(4);
//                rightLed.Update();
//                backUpRight = false;
//                }
//                }
//                }
//                }
//
//                leftServoMotor.Update();
//                rightServoMotor.Update();


