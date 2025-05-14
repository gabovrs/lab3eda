import java.util.ArrayList;

public class Main {
    public class Game {
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

    public class DateSet {
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
                    break;
                case "insertionSort":
                    break;
                case "selectionSort":
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

    }
}