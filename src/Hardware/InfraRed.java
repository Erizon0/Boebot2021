package Hardware;

import Interface.IRCallback;
import Interface.Update;
import TI.BoeBot;
import TI.PinMode;
import java.util.Arrays;

/**
 * A class for InfraRed
 */
public class InfraRed implements Update {

    private int pin;
    private IRCallback callback;

    /** Construct InfraRed
     * @param pin What pin the infrared sensor is on
     */
    public InfraRed(int pin) {
        this.pin = pin;
        BoeBot.setMode(this.pin, PinMode.Input);
    }

    /** **IMPORTANT** Set the callback of InfraRed
     * @param callback Where InfraRed should call back to
     */
    public void setCallback(IRCallback callback) {
        this.callback = callback;
    }

    /** Get the number from an array of received values
     * @param numbers An int array of received numbers
     * @return The converted number
     */
    private int getNumber(int[] numbers) {
        int[] shortNumbers = Arrays.copyOfRange(numbers, 0, 8);

        //Reverse the first 8 bits of the received int array
        for(int i = 0; i < shortNumbers.length / 2; i++)
        {
            int tempArray = shortNumbers[i];
            shortNumbers[i] = shortNumbers[shortNumbers.length - i - 1];
            shortNumbers[shortNumbers.length - i - 1] = tempArray;
        }

        //Put the int array into a string
        String byteNumbers = "";
        for (int number : shortNumbers) {
            byteNumbers += number;
        }

        //Parse the numbers to a decimal number
        System.out.println(Integer.parseInt(byteNumbers,2));
        return Integer.parseInt(byteNumbers,2);
    }

    @Override
    public void update() {
        int pulseLen = BoeBot.pulseIn(this.pin, false, 60000);

            if (pulseLen > 2000) {
                int lengths[] = new int[12];

                // Differentiate zeros and ones
                for (int i = 0; i < 12; i++) {
                    if (BoeBot.pulseIn(this.pin, false, 20000) > 1000) {
                        lengths[i] = 1;
                    } else {
                        lengths[i] = 0;
                    }
                }

                callback.onRemotePress(getNumber(lengths));
        }

    }
}
