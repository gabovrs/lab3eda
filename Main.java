
public class Main {
    public static void main(String[] args) {
        Benchmark.runSortBenchmarks("data/games_100.csv", "100 elementos");
        Benchmark.runCountingSort("data/games_100.csv", "100 elementos");

        Benchmark.runSortBenchmarks("data/games_10000.csv", "10.000 elementos");
        Benchmark.runCountingSort("data/games_10000.csv", "10.000 elementos");

        Benchmark.runSortBenchmarks("data/games_1000000.csv", "1.000.000 elementos");
        Benchmark.runCountingSort("data/games_1000000.csv", "1.000.000 elementos");

        Benchmark.runSearchBenchmarks("data/games_1000000.csv");
    }
}