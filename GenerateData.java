import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GenerateData {
    static Random random = new Random();
    static String[] names = { "Dragon", "Empire", "Quest", "Galaxy", "Legends", "Warrior" };
    static String[] categories = { "Action", "Adventure", "RPG", "Strategy", "Simulation" };

    private static String generateRandomName() {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(names.length);
            name.append(names[index]);
        }
        return name.toString();
    }

    public static ArrayList<Game> generate(int numGames) {
        ArrayList<Game> games = new ArrayList<>();
        for (int i = 0; i < numGames; i++) {
            String name = generateRandomName();
            String category = categories[(int) (Math.random() * categories.length)];
            int price = (random.nextInt(69) + 1) * 1000;
            int quality = random.nextInt(100);
            games.add(new Game(name, category, price, quality));
        }
        return games;
    }

    public static void saveToFile(ArrayList<Game> games, String filename) {
        try (PrintWriter writer = new PrintWriter(new File(filename))) {
            for (Game g : games) {
                writer.println(g.toCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Game> loadGames(String filename) {
        ArrayList<Game> games = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                if (parts.length != 4) continue;
                String name = parts[0];
                String category = parts[1];
                int price = Integer.parseInt(parts[2]);
                int quality = Integer.parseInt(parts[3]);
                games.add(new Game(name, category, price, quality));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return games;
    }

    public static void printGames(ArrayList<Game> games) {
        for (Game game : games) {
            System.out.println("Name: " + game.getName() + ", Category: " + game.getCategory() +
                    ", Price: " + game.getPrice() + ", Quality: " + game.getQuality());
        }
    }

    public static void main(String[] args) {
        int[] numGames = {100, 10000, 1000000};
        for (int n : numGames) {
            ArrayList<Game> games = generate(n);
            String filename = "data/games_" + n + ".csv";
            saveToFile(games, filename);
            System.out.println("Generated " + n + " games and saved to " + filename);
        }
    }
}
