package State;

import Hardware.*;
import Hardware.Button;
import Interface.DriveState;

import java.awt.*;

public class Collision implements DriveState {

    Button ES = new Button(0);

    ServoMotor leftServoMotor = new ServoMotor(13,-1);
    ServoMotor rightServoMotor = new ServoMotor(12,1);
    ServoControl servoControl = new ServoControl(leftServoMotor, rightServoMotor, this);

    Whisker leftWhisker = new Whisker(3);
    Whisker rightWhisker = new Whisker(4);
    Buzzer buzzer = new Buzzer(10, 50, 250);

    Led rightLed = new Led(0, Color.red, 500);
    Led leftLed = new Led (5, Color.white, 500);
    Led backLed = new Led (2, Color.white, 500);
    Led backLed1 = new Led (3, Color.red, 500);

    private int blinkDirection = 0;

    public void init(){
        servoControl.instantStop();
        rightLed.turnOn();
        leftLed.turnOn();
        backLed.turnOn();
        backLed1.turnOn();
        buzzer.stopBeep();
    }

    //TODO: Fill in the 3 empty methods
    //Add code to make leds blink left
    @Override
    public void turnLeftToggle() {
        //backLed.toggle();
    }

    //Add code to make leds blink right
    @Override
    public void turnRightToggle() {

    }

    //Add code to make buzzer buzz
    @Override
    public void buzzerToggle() {

    }

    //TODO: Check if this works
    @Override
    public void drive() {
        while(!ES.isPressed()){
            this.servoControl.instantStop();
        }
        init();

        if (!ES.isPressed()) {
            servoControl.goToSpeed(5);
        }

        if (leftWhisker.isPressed()) {
            servoControl.turnBackRight();
        } else if (rightWhisker.isPressed()) {
            servoControl.turnBackLeft();
        }

    }

    @Override
    public int getTurnDirection() {
        return blinkDirection;
    }

    @Override
    public void setTurnDirection(int dir) {
        this.blinkDirection = dir;
    }
}
