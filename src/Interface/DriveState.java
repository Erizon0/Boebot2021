package Interface;

//Interface for different kinds op states
public interface DriveState {
    void drive();
    void turnLeftToggle();
    void turnRightToggle();
    void buzzerToggle();

    int getTurnDirection();
    void setTurnDirection(int dir);
}
