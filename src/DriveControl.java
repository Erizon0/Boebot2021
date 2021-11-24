import TI.BoeBot;
import TI.PinMode;
import TI.Timer;

public class DriveControl implements Updatable {

    private ServoMotor leftServoMotor;
    private ServoMotor rightServoMotor;

    private Whisker leftWhisker;
    private Whisker rightWhisker;

    private Timer turnTimer = new Timer(500);

    private Button button = new Button(0);

    public DriveControl(ServoMotor leftServoMotor, ServoMotor rightServoMotor, Whisker leftWhisker, Whisker rightWhisker) {
        this.leftServoMotor = leftServoMotor;
        this.rightServoMotor = rightServoMotor;
        this.leftWhisker = leftWhisker;
        this.rightWhisker = rightWhisker;
        BoeBot.setMode(0, PinMode.Input);

    }

    @Override
    public void Update() {
        if(button.isPressed()){
            leftServoMotor.stop();
            rightServoMotor.stop();

            return;
        }
        if(leftWhisker.isPressed()||rightWhisker.isPressed()){
            leftServoMotor.goToSpeed(100);
            rightServoMotor.goToSpeed(100);
        }



////        else {
////            if (leftWhisker.isPressed() && rightWhisker.isPressed()) {
////                if (leftWhisker.isPressed() && !turnTimer.timeout()) {
////                    turnTimer.mark();
////                    leftServoMotor.goToSpeed(-100);
////                    rightServoMotor.goToSpeed(100);
////                } else if (turnTimer.timeout()) {
////                    leftServoMotor.goToSpeed(100);
////                    rightServoMotor.goToSpeed(100);
////                }
////
////                if (rightWhisker.isPressed()) {
////                    turnTimer.mark();
////                    leftServoMotor.goToSpeed(100);
////                    rightServoMotor.goToSpeed(-100);
////                } else if (turnTimer.timeout()){
////                    leftServoMotor.goToSpeed(100);
////                    rightServoMotor.goToSpeed(100);
////                }
////            }
//
//        }
        leftServoMotor.Update();
        rightServoMotor.Update();
        //leftWhisker.Update();
        //rightWhisker.Update();
    }

}
