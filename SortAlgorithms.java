import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortAlgorithms {

    public static void bubbleSort(ArrayList<Game> list, Comparator<Game> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0)
                    Collections.swap(list, j, j + 1);
    }

    public static void insertionSort(ArrayList<Game> list, Comparator<Game> comparator) {
        for (int i = 1; i < list.size(); i++) {
            Game key = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static void selectionSort(ArrayList<Game> list, Comparator<Game> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++)
                if (comparator.compare(list.get(j), list.get(min)) < 0)
                    min = j;
            Collections.swap(list, i, min);
        }
    }

    public static ArrayList<Game> mergeSort(ArrayList<Game> list, Comparator<Game> comparator) {
        if (list.size() <= 1) return list;
        int mid = list.size() / 2;
        ArrayList<Game> left = mergeSort(new ArrayList<>(list.subList(0, mid)), comparator);
        ArrayList<Game> right = mergeSort(new ArrayList<>(list.subList(mid, list.size())), comparator);
        return merge(left, right, comparator);
    }

    private static ArrayList<Game> merge(ArrayList<Game> left, ArrayList<Game> right, Comparator<Game> comp) {
        ArrayList<Game> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (comp.compare(left.get(i), right.get(j)) <= 0)
                result.add(left.get(i++));
            else
                result.add(right.get(j++));
        }
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }

    public static void quickSort(ArrayList<Game> list, int low, int high, Comparator<Game> comparator) {
        if (low < high) {
            int pi = partition(list, low, high, comparator);
            quickSort(list, low, pi - 1, comparator);
            quickSort(list, pi + 1, high, comparator);
        }
    }

    private static int partition(ArrayList<Game> list, int low, int high, Comparator<Game> comparator) {
        Game pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++)
            if (comparator.compare(list.get(j), pivot) < 0)
                Collections.swap(list, ++i, j);
        Collections.swap(list, i + 1, high);
        return i + 1;
    }
}
