package Interface;

/**
 * Interface for callbacks from infrared
 */
public interface IRCallback {
    /** Method for handling a remote signal
     * @param value What value was recieved
     */
    void onRemotePress(int value);
}
