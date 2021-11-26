package Hardware;

import TI.BoeBot;
import TI.PinMode;
import TI.Timer;

import java.awt.*;

//TODO refactor class

public class DriveControl implements Update {

    private ServoMotor leftServoMotor;
    private ServoMotor rightServoMotor;

    private Whisker leftWhisker;
    private Whisker rightWhisker;

    private boolean backUpLeft = false;
    private boolean backUpRight = false;

    private Timer turnTimer = new Timer(1000);
    private Timer stopTimer = new Timer(2000);
    private Timer backUpTimer = new Timer(3000);

    private Button button = new Button(0);
    private int state = -1;

    private Buzzer buzzer;

    private Led rightLed = new Led(0, Color.red, 500);
    private Led leftLed = new Led(5, Color.white, 500);
    private Led backLed = new Led(2, Color.white,500);
    private Led backLed1 = new Led(3, Color.red, 500);

    public DriveControl(ServoMotor leftServoMotor, ServoMotor rightServoMotor, Whisker leftWhisker, Whisker rightWhisker) {
        this.leftServoMotor = leftServoMotor;
        this.rightServoMotor = rightServoMotor;
        this.leftWhisker = leftWhisker;
        this.rightWhisker = rightWhisker;
        BoeBot.setMode(0, PinMode.Input);
        this.buzzer = new Buzzer(10, 200, 500);
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

    @Override
    public void update() {
        switch (this.state) {
            case 0:
                leftServoMotor.goToSpeed(3);
                rightServoMotor.goToSpeed(3);
                if (rightWhisker.isPressed()) {
                    this.backUpRight = true;
                    this.state = 1;
                    this.backUpTimer.mark();
                    this.stopTimer.mark();
                }
                if (leftWhisker.isPressed()) {
                    this.backUpLeft = true;
                    this.state = 1;
                    this.backUpTimer.mark();
                    this.stopTimer.mark();
                }
                break;

            case 1:
                stop();
                if(stopTimer.timeout()) {
                    backward();
                    buzzer.update();
                    this.buzzer.update();
                    if (backUpTimer.timeout())
                        this.state = 2;
                    stopTimer.mark();
                }
                break;

            case 2:
                stop();
                this.buzzer.stopBeep();
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

            default:
                leftServoMotor.stop();
                rightServoMotor.stop();
                rightLed.turnOn();
                leftLed.turnOn();
                backLed.turnOn();
                backLed1.turnOn();
                buzzer.stopBeep();
//                System.out.println("IK MAG NIET RIJDEN");
                if (!button.isPressed())
                    this.state = 0;
                break;
        }

        if(button.isPressed()){
            this.state = -1;
        }
        System.out.println("current state: " + this.state);
        System.out.println("current speed right: " + rightServoMotor.getSpeed());
        System.out.println("current speed left: " + leftServoMotor.getSpeed());
        System.out.println("Rightwisker = " + this.rightWhisker.isPressed());
        System.out.println("Leftwisker = " + this.leftWhisker.isPressed());
//        BoeBot.wait(2000);
        leftServoMotor.update();
        rightServoMotor.update();
    }
}