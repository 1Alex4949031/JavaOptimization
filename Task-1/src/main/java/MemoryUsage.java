import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemoryUsage {
  public static void memoryUsage() throws IOException {
    List<Triangle> triangles = new ArrayList<>();
    Runtime runtime = Runtime.getRuntime();

    FileWriter writer = new FileWriter("memory_usage.csv");
    writer.append("Used Memory,Free Memory,Total Memory,Max Memory\n");

    try {
      for (int i = 0; ; i++) {
        triangles.add(new Triangle(i + 1, i, i + 2));
        if (i % 100000 == 0) {
          long[] stats = printMemoryStats(runtime);
          writer.append(String.join(",", String.valueOf(stats[0]), String.valueOf(stats[1]),
              String.valueOf(stats[2]), String.valueOf(stats[3]))).append("\n");
        }
      }
    } catch (OutOfMemoryError e) {
      System.out.println("Out of memory error.");
    } finally {
      writer.flush();
      writer.close();
    }
  }

  private static long[] printMemoryStats(Runtime runtime) {
    long freeMemory = runtime.freeMemory();
    long totalMemory = runtime.totalMemory();
    long maxMemory = runtime.maxMemory();
    long usedMemory = totalMemory - freeMemory;

    System.out.println("Memory used: " + usedMemory + " bytes");
    System.out.println("Free memory: " + freeMemory + " bytes");
    System.out.println("Total memory: " + totalMemory + " bytes");
    System.out.println("Max memory: " + maxMemory + " bytes");
    System.out.println("---------------------------------");

    return new long[]{usedMemory, freeMemory, totalMemory, maxMemory};
  }
}
