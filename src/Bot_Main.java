import TI.BoeBot;
import javafx.beans.binding.BooleanExpression;

public class Bot_Main {

    //We need to have a place to initialize all hardware
    public static void main(String[] args) {

        ServoMotor leftServoMotor = new ServoMotor(13, -1);
        ServoMotor rightServoMotor = new ServoMotor(12,1);

        Whisker leftWhisker = new Whisker(1);
        Whisker rightWhisker = new Whisker(4);

        DriveControl driveControl = new DriveControl(leftServoMotor, rightServoMotor, leftWhisker, rightWhisker);

        while (true) {
            driveControl.Update();
            BoeBot.wait(1);
        }

    }

}
