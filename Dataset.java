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
        for (Game game : data) {
            if (game.getPrice() >= low && game.getPrice() <= high)
                result.add(game);
        }
        return result;
    }

    public ArrayList<Game> getGamesByCategory(String category) {
        ArrayList<Game> result = new ArrayList<>();
        if (sortedByAttribute.equals("category")) {
            for (Game game : data)
                if (game.getCategory().equals(category))
                    result.add(game);
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
            for (Game game : data)
                if (game.getQuality() == quality)
                    result.add(game);
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
