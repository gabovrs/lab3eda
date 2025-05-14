import java.util.ArrayList;

public class Main {

    public static class GenerateData {
        String[] names = {"Dragon", "Empire", "Quest", "Galaxy", "Legends", "Warrior"};
        String[] categories = {"Action", "Adventure", "RPG", "Strategy", "Simulation"};

        private String generateRandomName() {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < 2; i++) {
                name.append(names[(int) (Math.random() * names.length)]);
            }
            return name.toString();
        }

        public ArrayList<Game> generateData(int numGames) {
            ArrayList<Game> games = new ArrayList<>();
            for (int i = 0; i < numGames; i++) {
                String name = generateRandomName();
                String category = categories[(int) (Math.random() * categories.length)];
                int price = ((int) (Math.random() * 69) + 1) * 1000;
                int quality = (int) (Math.random() * 100);
                games.add(new Game(name, category, price, quality));
            }
            return games;
        }

        public void printGames(ArrayList<Game> games) {
            for (Game game : games) {
                System.out.println("Name: " + game.getName() + ", Category: " + game.getCategory() +
                        ", Price: " + game.getPrice() + ", Quality: " + game.getQuality());
            }
        }
    }

    public static class Game {
        private String name;
        private String category;
        private int price;
        private int quality;

        public Game(String name, String category, int price, int quality) {
            this.name = name;
            this.category = category;
            this.price = price;
            this.quality = quality;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public int getPrice() {
            return price;
        }

        public int getQuality() {
            return quality;
        }
    }

    public static class DateSet {
        private ArrayList<Game> data;
        private String sortedByAttribute;

        public DateSet() {
            this.data = new ArrayList<>();
            this.sortedByAttribute = "";
        }

        ArrayList<Game> getGamesByPrice(int price) {
            ArrayList<Game> result = new ArrayList<>();
            for (Game game : data) {
                if (game.getPrice() == price) {
                    result.add(game);
                }
            }
            return result;
        }

        ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherPrice) {
            ArrayList<Game> result = new ArrayList<>();
            for (Game game : data) {
                if (game.getPrice() >= lowerPrice && game.getPrice() <= higherPrice) {
                    result.add(game);
                }
            }
            return result;
        }

        ArrayList<Game> getGamesByCategory(String category) {
            ArrayList<Game> result = new ArrayList<>();
            for (Game game : data) {
                if (game.getCategory().equalsIgnoreCase(category)) {
                    result.add(game);
                }
            }
            return result;
        }

        ArrayList<Game> getGamesByQuality(int quality) {
            ArrayList<Game> result = new ArrayList<>();
            for (Game game : data) {
                if (game.getQuality() == quality) {
                    result.add(game);
                }
            }
            return result;
        }

        public void sortByAlgorithm(String algorithm, String attribute) {
            this.sortedByAttribute = attribute;
            switch (algorithm) {
                case "bubbleSort":
                    for (int i = 0; i < data.size() - 1; i++) {
                        for (int j = 0; j < data.size() - i - 1; j++) {
                            if (attribute == "price" && data.get(j).getPrice() > data.get(j + 1).getPrice()) {
                                Game temp = data.get(j);
                                data.set(j, data.get(j + 1));
                                data.set(j + 1, temp);
                            } else if (attribute == "quality" && data.get(j).getQuality() > data.get(j + 1).getQuality()) {
                                Game temp = data.get(j);
                                data.set(j, data.get(j + 1));
                                data.set(j + 1, temp);
                            }
                        }
                    }
                    break;
                case "insertionSort":
                    for (int i = 1; i < data.size(); i++) {
                        Game key = data.get(i);
                        int j = i - 1;
                        while (j >= 0 && ((attribute == "price" && data.get(j).getPrice() > key.getPrice()) ||
                                (attribute == "quality" && data.get(j).getQuality() > key.getQuality()))) {
                            data.set(j + 1, data.get(j));
                            j--;
                        }
                        data.set(j + 1, key);
                    }
                    break;
                case "selectionSort":
                    for (int i = 0; i < data.size() - 1; i++) {
                        int minIndex = i;
                        for (int j = i + 1; j < data.size(); j++) {
                            if (attribute == "price" && data.get(j).getPrice() < data.get(minIndex).getPrice()) {
                                minIndex = j;
                            } else if (attribute == "quality" && data.get(j).getQuality() < data.get(minIndex).getQuality()) {
                                minIndex = j;
                            }
                        }
                        Game temp = data.get(minIndex);
                        data.set(minIndex, data.get(i));
                        data.set(i, temp);
                    }
                    break;
                case "mergeSort":
                    break;
                case "quickSort":
                    break;
                default:

                    break;
            }
        }
    }

    public static void main(String[] args) {
        GenerateData generateData = new GenerateData();
        ArrayList<Game> games = generateData.generateData(100);
        generateData.printGames(games);
    }
}