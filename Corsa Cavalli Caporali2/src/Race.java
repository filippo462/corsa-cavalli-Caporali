import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Race {
    private List<Horse> horses;
    private int trackLength;
    private List<Horse> leaderboard;

    public Race(int trackLength) {
        this.trackLength = trackLength;
        this.horses = new ArrayList<>();
        this.leaderboard = new ArrayList<>();
    }

    public void addHorse(String name, int speed) {
        horses.add(new Horse(name, trackLength, speed, this));
    }

    public synchronized void recordFinish(Horse horse) {
        leaderboard.add(horse);
    }

    public void startRace() {
        for (Horse horse : horses) {
            horse.start();
        }

        for (Horse horse : horses) {
            try {
                horse.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("The race is over!");
        showLeaderboard();
    }

    private void showLeaderboard() {
        leaderboard.sort(Comparator.comparingInt(Horse::getDistanceTraveled).reversed());

        System.out.println("Top 3 horses:");
        for (int i = 0; i < Math.min(3, leaderboard.size()); i++) {
            System.out.println((i + 1) + ". " + leaderboard.get(i).getName() + " with " + leaderboard.get(i).getDistanceTraveled() + " meters traveled");
        }
    }

    public void saveResultsToFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write("Race results:\n");
            for (int i = 0; i < Math.min(3, leaderboard.size()); i++) {
                writer.write((i + 1) + ". " + leaderboard.get(i).getName() + " with " + leaderboard.get(i).getDistanceTraveled() + " meters traveled\n");
            }
            writer.write("--------------\n");
            System.out.println("Results saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving results to file: " + e.getMessage());
        }
    }
}
