package Interface;

/**
 * Interface for callbacks from servo's
 */
public interface ServoCallback {
    /**
     * Let the left LEDs blink
     */
    void turnLeftOn();

    /**
     * Don't let the left LEDs blink
     */
    void turnLeftOff();

    /**
     * Let the right LEDs blink
     */
    void turnRightOn();

    /**
     * Don't let the right LEDs blink
     */
    void turnRightOff();

    /** Set the buzzer state to the given parameter
     * @param bool Signifies if the buzzer should be on or off
     */
    void buzzerToggle(boolean bool);

    /**
     * Reset all LEDs
     */
    void resetLed();

    /**
     * @param bool Signifies if the bot should be driving or not
     */
    void setDriving(boolean bool);
}
