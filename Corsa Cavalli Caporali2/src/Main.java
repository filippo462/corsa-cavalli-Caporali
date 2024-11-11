import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the length of the track in meters: ");
        int trackLength = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        Race race = new Race(trackLength);
        System.out.print("Enter the number of horses: ");
        int numberOfHorses = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        for (int i = 0; i < numberOfHorses; i++) {
            System.out.print("Enter the name of horse " + (i + 1) + ": ");
            String horseName = scanner.nextLine();
            System.out.print("Enter the speed of the horse (meters per second): ");
            int speed = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()
            race.addHorse(horseName, speed);
        }

        race.startRace();

        System.out.print("Enter the full file path to save the results: ");
        String filePath = scanner.nextLine();
        race.saveResultsToFile(filePath);

        scanner.close();
    }
}
