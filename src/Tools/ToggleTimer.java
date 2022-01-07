package Tools;

/**
 * A timer you can turn on or off, starts disabled
 */
public class ToggleTimer {

    private int interval;
    private long lasttick;
    private boolean isActive;

    /** Construct a ToggleTimer
     * @param interval The interval the timer should timeout on
     */
    public ToggleTimer(int interval) {
        this.interval = interval;
        this.lasttick = System.currentTimeMillis();
        this.isActive = false;
    }

    /** Check if the timer had timed out
     * @return If the timer has timed out or not
     */
    public boolean timeout() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime > this.lasttick + (long)this.interval) && isActive) {
            this.lasttick += (long)this.interval;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Start the timer
     */
    public void mark() {
        this.lasttick = System.currentTimeMillis();
    }

    /** Set the timer on or off
     * @param bool If the timer should be on or off
     */
    public void setActive(boolean bool) {
        this.isActive = bool;
    }

    /** Change the time the timer should timeout on, disables the timer
     * @param interval The interval the timer should timeout ont
     */
    public void setInterval(int interval) {
        this.interval = interval;
        this.lasttick = System.currentTimeMillis();
        this.isActive = false;
    }

}
