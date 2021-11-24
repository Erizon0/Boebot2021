import java.util.ArrayList;

public class Bot_Main {


    private DriveControl driveControl;
    private ArrayList<Updatable> updatables = new ArrayList<>();


    public static void main(String[] args) {
        Bot_Main bm = new Bot_Main();
        try{
            bm.init();
            bm.run();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    /*
    method for initialsing everything
     */
    public void init(){
        ServoMotor leftServoMotor = new ServoMotor(1, 1);
        ServoMotor rightServoMotor = new ServoMotor(1,1);

        Whisker leftWhisker = new Whisker(1);
        Whisker rightWhisker = new Whisker(1);

        this.driveControl = new DriveControl(leftServoMotor, rightServoMotor, leftWhisker, rightWhisker);
    }

    /*
    loop for running everything
     */
    public void run() throws InterruptedException{
        while (true) {
            for(Updatable update : this.updatables )
                update.Update();

            //todo remove VVVVVVVVVVV
            this.driveControl.Update();
        }
    }
}
