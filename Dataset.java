import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Dataset {
    private ArrayList<Game> data;
    private String sortedByAttribute = "";

    public Dataset(ArrayList<Game> data) {
        this.data = data;
    }

    ArrayList<Game> getGamesByPrice(int price) {
        ArrayList<Game> result = new ArrayList<>();
        if (sortedByAttribute.equals("price")) {
            int idx = binarySearchByPrice(price);
            if (idx >= 0) {
                for (int i = idx; i < data.size() && data.get(i).getPrice() == price; i++)
                    result.add(data.get(i));
                for (int i = idx - 1; i >= 0 && data.get(i).getPrice() == price; i--)
                    result.add(data.get(i));
            }
        } else {
            for (Game game : data)
                if (game.getPrice() == price)
                    result.add(game);
        }
        return result;
    }

    public ArrayList<Game> getGamesByPriceRange(int low, int high) {
        ArrayList<Game> result = new ArrayList<>();

        if (sortedByAttribute.equals("price")) {
            int lowIndex = 0, highIndex = data.size() - 1;
            while (lowIndex <= highIndex) {
                int mid = (lowIndex + highIndex) / 2;
                if (data.get(mid).getPrice() < low) {
                    lowIndex = mid + 1;
                } else if (data.get(mid).getPrice() > high) {
                    highIndex = mid - 1;
                } else {
                    int left = mid;
                    while (left >= 0 && data.get(left).getPrice() >= low && data.get(left).getPrice() <= high) {
                        result.add(data.get(left));
                        left--;
                    }
                    int right = mid + 1;
                    while (right < data.size() && data.get(right).getPrice() >= low
                            && data.get(right).getPrice() <= high) {
                        result.add(data.get(right));
                        right++;
                    }
                    break;
                }
            }
        } else {
            for (Game game : data)
                if (game.getPrice() >= low && game.getPrice() <= high)
                    result.add(game);
        }

        return result;
    }

    public ArrayList<Game> getGamesByCategory(String category) {
        ArrayList<Game> result = new ArrayList<>();

        if (sortedByAttribute.equals("category")) {
            int low = 0, high = data.size() - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (data.get(mid).getCategory().equals(category)) {
                    int left = mid;
                    while (left >= 0 && data.get(left).getCategory().equals(category)) {
                        result.add(data.get(left));
                        left--;
                    }
                    int right = mid + 1;
                    while (right < data.size() && data.get(right).getCategory().equals(category)) {
                        result.add(data.get(right));
                        right++;
                    }
                    break;
                } else if (data.get(mid).getCategory().compareTo(category) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        } else {
            for (Game game : data)
                if (game.getCategory().equals(category))
                    result.add(game);
        }

        return result;
    }

    public ArrayList<Game> getGamesByQuality(int quality) {
        ArrayList<Game> result = new ArrayList<>();
        if (sortedByAttribute.equals("quality")) {
            int low = 0, high = data.size() - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (data.get(mid).getQuality() == quality) {
                    int left = mid;
                    while (left >= 0 && data.get(left).getQuality() == quality) {
                        result.add(data.get(left));
                        left--;
                    }
                    int right = mid + 1;
                    while (right < data.size() && data.get(right).getQuality() == quality) {
                        result.add(data.get(right));
                        right++;
                    }
                    break;
                } else if (data.get(mid).getQuality() < quality) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        } else {
            for (Game game : data)
                if (game.getQuality() == quality)
                    result.add(game);
        }
        return result;
    }

    public void sortByAlgorithm(String algorithm, String attribute) {
        Comparator<Game> comparator = switch (attribute) {
            case "price" -> Comparator.comparingInt(g -> g.getPrice());
            case "category" -> Comparator.comparing(g -> g.getCategory());
            case "quality" -> Comparator.comparingInt(g -> g.getQuality());
            default -> Comparator.comparingInt(g -> g.getPrice());
        };

        switch (algorithm) {
            case "bubbleSort" -> SortAlgorithms.bubbleSort(data, comparator);
            case "insertionSort" -> SortAlgorithms.insertionSort(data, comparator);
            case "selectionSort" -> SortAlgorithms.selectionSort(data, comparator);
            case "mergeSort" -> data = SortAlgorithms.mergeSort(data, comparator);
            case "quickSort" -> SortAlgorithms.quickSort(data, 0, data.size() - 1, comparator);
            default -> data.sort(comparator);
        }

        sortedByAttribute = attribute;
    }

    private int binarySearchByPrice(int price) {
        int low = 0, high = data.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (data.get(mid).getPrice() == price)
                return mid;
            if (data.get(mid).getPrice() < price)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }

    public void countingSortByQuality() {
        int maxQuality = 100;
        List<List<Game>> buckets = new ArrayList<>(maxQuality + 1);
        for (int i = 0; i <= maxQuality; i++)
            buckets.add(new ArrayList<>());

        for (Game g : data)
            buckets.get(g.getQuality()).add(g);

        ArrayList<Game> sorted = new ArrayList<>();
        for (List<Game> bucket : buckets)
            sorted.addAll(bucket);

        data = sorted;
        sortedByAttribute = "quality";
    }

}
