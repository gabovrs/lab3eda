import java.util.ArrayList;

public class Benchmark {
    static String[] sortAlgorithms = {
        "bubbleSort", "insertionSort", "selectionSort",
        "mergeSort", "quickSort", "collectionsSort"
    };
    static String[] attributes = {"price", "category", "quality"};

    public static long benchmarkSort(Dataset dataset, String algorithm, String attribute) {
        long start = System.currentTimeMillis();
        dataset.sortByAlgorithm(algorithm, attribute);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long benchmarkSearchPrice(Dataset dataset, int price) {
        long start = System.currentTimeMillis();
        dataset.getGamesByPrice(price);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long benchmarkSearchRange(Dataset dataset, int low, int high) {
        long start = System.currentTimeMillis();
        dataset.getGamesByPriceRange(low, high);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long benchmarkSearchCategory(Dataset dataset, String category) {
        long start = System.currentTimeMillis();
        dataset.getGamesByCategory(category);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long benchmarkSearchQuality(Dataset dataset, int quality) {
        long start = System.currentTimeMillis();
        dataset.getGamesByQuality(quality);
        long end = System.currentTimeMillis();
        return end - start;
    }


    public static void runSortBenchmarks(String datasetPath, String datasetName) {
        System.out.println("====== Benchmark Ordenamiento (" + datasetName + ") ======");
        for (String attribute : attributes) {
            System.out.println("\n>> Atributo: " + attribute);
            System.out.printf("%-15s %-20s\n", "Algoritmo", "Tiempo promedio (ms)");
            for (String algorithm : sortAlgorithms) {
                long totalTime = 0;
                for (int i = 0; i < 3; i++) {
                    ArrayList<Game> games = GenerateData.loadGames(datasetPath);
                    Dataset ds = new Dataset(games);
                    long time = benchmarkSort(ds, algorithm, attribute);
                    totalTime += time;
                }
                long avgTime = totalTime / 3;
                String label = avgTime + "";
                System.out.printf("%-15s %-20s\n", algorithm, label);
            }
        }
    }

    public static void runCountingSort(String datasetPath, String datasetName) {
        System.out.println("\n====== Counting Sort por Quality (" + datasetName + ") ======");
        long totalTime = 0;
        for (int i = 0; i < 3; i++) {
            ArrayList<Game> games = GenerateData.loadGames(datasetPath);
            Dataset ds = new Dataset(games);
            long start = System.currentTimeMillis();
            ds.countingSortByQuality();
            long end = System.currentTimeMillis();
            totalTime += (end - start);
        }
        long avgTime = totalTime / 3;
        System.out.println("Tiempo promedio: " + avgTime + " ms");
    }

    public static void runSearchBenchmarks(String datasetPath) {
        System.out.println("\n====== Benchmark Búsqueda (solo con dataset grande) ======");
        ArrayList<Game> original = GenerateData.loadGames(datasetPath);
        Dataset ds = new Dataset(new ArrayList<>(original)); // NO ORDENADO

        System.out.println(">> Búsqueda lineal:");
        System.out.println("getGamesByPrice: " + benchmarkSearchPrice(ds, 5000) + " ms");
        System.out.println("getGamesByPriceRange: " + benchmarkSearchRange(ds, 5000, 10000) + " ms");
        System.out.println("getGamesByCategory: " + benchmarkSearchCategory(ds, "RPG") + " ms");
        System.out.println("getGamesByQuality: " + benchmarkSearchQuality(ds, 80) + " ms");

        Dataset dsSorted = new Dataset(new ArrayList<>(original));
        dsSorted.sortByAlgorithm("mergeSort", "price");
        System.out.println("\n>> Búsqueda binaria (ordenado por price):");
        System.out.println("getGamesByPrice: " + benchmarkSearchPrice(dsSorted, 5000) + " ms");
        System.out.println("getGamesByPriceRange: " + benchmarkSearchRange(dsSorted, 5000, 10000) + " ms");

        dsSorted.sortByAlgorithm("mergeSort", "category");
        System.out.println("getGamesByCategory: " + benchmarkSearchCategory(dsSorted, "RPG") + " ms");

        dsSorted.sortByAlgorithm("mergeSort", "quality");
        System.out.println("getGamesByQuality: " + benchmarkSearchQuality(dsSorted, 80) + " ms");
    }
}
