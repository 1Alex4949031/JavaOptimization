public class Main {
  private static final int DIMENSION_MATRIX = 1028;

  static {
    System.loadLibrary("MatrixMultiplier");
  }

  public static void main(String[] args) {
    MatrixMultiplier multiplier = new MatrixMultiplier();

    // Генерируем матрицы
    int[][] a = multiplier.generateRandomMatrix(DIMENSION_MATRIX, DIMENSION_MATRIX);
    int[][] b = multiplier.generateRandomMatrix(DIMENSION_MATRIX, DIMENSION_MATRIX);

    // Замеряем быстродействие JNI
    long startTimeNative = System.currentTimeMillis();
    multiplier.multiplyMatricesNative(a, b);
    long endTimeNative = System.currentTimeMillis();
    System.out.println("Time using JNI: " + (endTimeNative - startTimeNative) + " ms.");

    // Замеряем быстродействие Java
    long startTimeJava = System.currentTimeMillis();
    multiplier.multiplyMatricesJava(a, b);
    long endTimeJava = System.currentTimeMillis();
    System.out.println("Time using Java: " + (endTimeJava - startTimeJava) + " ms.");
  }
}
