public class DriveControl implements Updatable {

    private ServoMotor leftServoMotor;
    private ServoMotor rightServoMotor;

    private Whisker leftWhisker;
    private Whisker rightWhisker;

    public DriveControl(ServoMotor leftServoMotor, ServoMotor rightServoMotor, Whisker leftWhisker, Whisker rightWhisker) {
        this.leftServoMotor = leftServoMotor;
        this.rightServoMotor = rightServoMotor;
        this.leftWhisker = leftWhisker;
        this.rightWhisker = rightWhisker;
    }

    @Override
    public void Update() {
        //leftServoMotor.Update();
        //rightServoMotor.Update();
        //leftWhisker.Update();
        //rightWhisker.Update();
    }

}
