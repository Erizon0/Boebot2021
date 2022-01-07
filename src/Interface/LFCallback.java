package Interface;

import Hardware.LineFollower;

/**
 * An interface for linefollower callbacks
 */
public interface LFCallback {
    /** Method for handling a linefollow trigger
     * @param source The source of the trigger
     * @param rightValue The value of the right linefollower
     * @param leftValue The value of the left linefollower
     */
    void onLineFollowTrigger(LineFollower source, int rightValue, int leftValue);
}
