package Hardware;


import Interface.Update;
import TI.Servo;
import TI.Timer;

/**
 * A class for a Gripper
 */
public class Gripper implements Update {

    private Servo gripper;
    private int gripperPosition;
    private boolean isOpen;
    private Timer gripperTimer;
    private Timer gripperMove = new Timer(500);

    /** Construct a Gripper
     * @param pin The pin the Gripper is connected to
     */
    public Gripper (int pin) {
        this.gripper = new Servo(pin);
        this.gripperPosition = 1500;
        this.isOpen = false;
        this.gripperTimer = new Timer(5);
        gripper.update(gripperPosition);
    }

    /**
     * Open the gripper
     */
    // this method sets the isOpen to true so the gripper will open
    public void open(){
        this.isOpen = true;
    }

    /**
     * Close the gripper
     */
    //this method set's the isOpen to false so the gripper will close
    public void close(){
        this.isOpen = false;
    }

    public void gripperMove(){
        if(this.gripperMove.timeout())
            this.gripperMove.mark();
        this.isOpen = !this.isOpen;
    }

    @Override
    //the update class will update te gripperState and the isOpen value of the servo
    public void update() {
        if(gripperTimer.timeout()) {
            gripperTimer.mark();
            if (isOpen && gripperPosition < 1620) { //1500 is fully open
                gripper.update(gripperPosition);
                gripperPosition++;

            } else if (!isOpen && gripperPosition > 1125) { //1100 is fully closed
                gripper.update(gripperPosition);
                gripperPosition--;
            }
        }
    }
}
