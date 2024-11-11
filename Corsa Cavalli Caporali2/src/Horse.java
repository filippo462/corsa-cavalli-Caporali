import java.util.Random;

public class Horse extends Thread {
    private String name;
    private int totalDistance;
    private int distanceTraveled = 0;
    private int speed; // Speed set by the user
    private boolean injured = false; // Indicates if the horse is injured
    private Random random;
    private Race race;

    public Horse(String name, int totalDistance, int speed, Race race) {
        this.name = name;
        this.totalDistance = totalDistance;
        this.speed = speed;
        this.race = race;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (distanceTraveled < totalDistance && !injured) {
            // Simulate an injury with a 10% chance
            if (random.nextInt(100) < 10) {
                injured = true;
                System.out.println(name + " got injured and is out of the race!");
                break;
            }

            // Move forward by the horse's speed
            distanceTraveled += speed;
            if (distanceTraveled > totalDistance) {
                distanceTraveled = totalDistance; // Limit to the total distance
            }
            System.out.println(name + " has traveled " + distanceTraveled + " meters.");

            try {
                Thread.sleep(1000); // Wait for one second before the next step
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!injured) {
            System.out.println(name + " has finished the race!");
            race.recordFinish(this);
        }
    }

    public String getHorseName() {
        return name;
    }

    public int getDistanceTraveled() {
        return distanceTraveled;
    }

    public boolean isInjured() {
        return injured;
    }
}
