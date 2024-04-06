public class AllocateMemory {

  public static void main(String[] args) {
    NativeMethods nativeMethods = new NativeMethods();

    long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    System.out.println("Used memory in bytes before allocation: " + beforeMemory);

    nativeMethods.allocateMemory();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    System.out.println("Used memory in bytes after allocation: " + afterMemory);

    System.out.println("Difference in memory usage: " + (afterMemory - beforeMemory) + " bytes");
  }
}
