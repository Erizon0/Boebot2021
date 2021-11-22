public class DriveControl implements Updatable {

    private ServoMotor leftServoMotor;
    private ServoMotor rightServoMotor;

    private Whisker leftWhisker;
    private Whisker rightWhisker;

    private Button button;

    public DriveControl(ServoMotor leftServoMotor, ServoMotor rightServoMotor, Whisker leftWhisker, Whisker rightWhisker) {
        this.leftServoMotor = leftServoMotor;
        this.rightServoMotor = rightServoMotor;
        this.leftWhisker = leftWhisker;
        this.rightWhisker = rightWhisker;
        this.button = new Button(0);
    }

    @Override
    public void Update() {
        button.Update();
        if(button.isPressed()){
            
        }
        //leftServoMotor.Update();
        //rightServoMotor.Update();
        //leftWhisker.Update();
        //rightWhisker.Update();
    }

}
