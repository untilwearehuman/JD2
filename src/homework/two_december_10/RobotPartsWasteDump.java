package homework.two_december_10;

import java.util.List;
import java.util.Random;

public class RobotPartsWasteDump {

    public static final String[] ROBOT_PARTS = {"Head", "Body", "Left hand", "Right hand", "Left leg", "Right leg",
            "CPU", "RAM", "HDD"};
    public static final int ONE_NIGHT_LENGTH = 100;
    public static final int TIME_OF_100_NIGHTS = 10000;
    private volatile int timePassed = 1;
    private volatile String wastedPart;
    private volatile String collectedPart;
    private final List<String> partList;

    public RobotPartsWasteDump(List<String> partList) {
        this.partList = partList;
    }

    public String getWastedPart() {
        return wastedPart;
    }

    public String getCollectedPart() {
        return collectedPart;
    }

    public List<String> getPartList() {
        return partList;
    }

    public int getTimePassed() {
        return timePassed;
    }

    public synchronized void throwPartToDump() {
            if (timePassed < TIME_OF_100_NIGHTS) {
                wastedPart = ROBOT_PARTS[new Random().nextInt(ROBOT_PARTS.length)];
                partList.add(wastedPart);
                timePassed = timePassed + ONE_NIGHT_LENGTH;
            }
            notifyAll();
    }

    public synchronized void takePartFromDump() {
            if (timePassed < TIME_OF_100_NIGHTS) {
                while (getPartList().size() < 1) {

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                collectedPart = partList.get(0);
                partList.remove(0);
            }
    }
}