import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String name;
        int score = 0, roundNumber = 1, doorBound = 2, ghostDoor, doorGuess;
        boolean alive = true;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter name: ");
        name = scanner.nextLine();

        System.out.println("""
                
                Welcome to ghost game!
                Guess a door and avoid the spooky ghost to stay alive
                """);

        while (alive) {
            System.out.println("ROUND " + roundNumber);
            ghostDoor = random.nextInt(1, doorBound);

            System.out.print("Guess a door between 1 and "+ (doorBound-1) +": ");
            doorGuess = scanner.nextInt();

            // checks if user inputs a correct door number
            while (doorGuess < 1 || doorGuess >= doorBound) {
                System.out.print("Enter a correct door number: ");
                doorGuess = scanner.nextInt();
            }

            // checks if guessed door isn't the ghost door
            if (doorGuess != ghostDoor) {
                System.out.println("You've dodged the ghost!\n");
                score += 1;
                roundNumber += 1;

            // checks if player is scared from the ghost
            } else {
                scanner.close();
                System.out.println("\nBoo!");
                System.out.println("Unlucky");
                System.out.println("Your final Score: "+score);
                alive = false;
            }
        }
        saveGame(name, score);
    }

    // Saves name and score in a text file
    public static void saveGame(String name, int score) {
        LocalTime localTime = LocalTime.now();
        String fileLocation = "src/save.txt";
        String saveString = name + ", " + score + ", " + localTime + "\n";

        // writes name and score into file
        try (FileWriter fileWriter = new FileWriter(fileLocation)) {
            fileWriter.write(saveString);

        } catch (IOException e) {
            System.out.println("Unable to save game!");
            throw new RuntimeException(e);
        }
    }
}
