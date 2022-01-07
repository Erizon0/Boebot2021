package Interface;

import Hardware.Ultrasone;

/**
 * Interface for callbacks from ultrasone
 */
public interface UltrasoneCallback {
    /** Method for handling ultrasone triggers
     * @param source Where the signal came from
     * @param value What distance in centimeters the robot is from the object
     */
    void onUltrasoneTrigger(Ultrasone source, int value);
}
