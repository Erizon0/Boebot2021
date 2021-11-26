package State;

import Hardware.*;
import Hardware.Button;

import java.awt.*;

public class Demo1 {

    Button ES = new Button(0);
    ServoMotor leftServoMotor = new ServoMotor(13,-1);
    ServoMotor rightServoMotor = new ServoMotor(12,1);
    Whisker leftWhisker = new Whisker(3);
    Whisker rightWhisker = new Whisker(4);
    Buzzer buzzer = new Buzzer(10, 50, 250);

    Led rightLed = new Led(0, Color.red, 500);
    Led leftLed = new Led (5, Color.white, 500);
    Led backLed = new Led (2, Color.white, 500);
    Led backLed1 = new Led (3, Color.red, 500);

    public void init(){
        leftServoMotor.stop();
        rightServoMotor.stop();
        rightLed.turnOn();
        leftLed.turnOn();
        backLed.turnOn();
        backLed1.turnOn();
        buzzer.stopBeep();
    }

    public Demo1(){
        while(ES.isPressed()){



        }
        init();
    }
}
