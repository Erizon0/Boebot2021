package Interface;

import Hardware.LineFollower;

public interface LFCallback {
    void onLineFollowTrigger(LineFollower source, int rightValue, int leftValue);
}
