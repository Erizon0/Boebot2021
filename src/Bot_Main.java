public class Bot_Main {

    public static void main(String[] args) {

        ServoMotor leftServoMotor = new ServoMotor(1, 1);
        ServoMotor rightServoMotor = new ServoMotor(1,1);

        Whisker leftWhisker = new Whisker(1);
        Whisker rightWhisker = new Whisker(1);

        DriveControl driveControl = new DriveControl(leftServoMotor, rightServoMotor, leftWhisker, rightWhisker);

        while (true) {
            driveControl.Update();
        }

    }

}
