public class MatrixMultiplier {
  static {
    System.loadLibrary("MatrixMultiplier");
  }

  // Нативный метод умножения матриц
  public native int[][] multiplyMatricesNative(int[][] a, int[][] b);

  // Java-метод умножения матриц
  @SuppressWarnings("all")
  public int[][] multiplyMatricesJava(int[][] a, int[][] b) {
    int aRows = a.length;
    int aColumns = a[0].length;
    int bRows = b.length;
    int bColumns = b[0].length;

    if (aColumns != bRows) {
      throw new IllegalArgumentException("Read about matrix multiplication!");
    }

    int[][] result = new int[aRows][bColumns];

    for (int i = 0; i < aRows; i++) {
      for (int j = 0; j < bColumns; j++) {
        for (int k = 0; k < aColumns; k++) {
          result[i][j] += a[i][k] * b[k][j];
        }
      }
    }

    return result;
  }

  // Метод для заполнения матриц случайными числами
  public int[][] generateRandomMatrix(int rows, int columns) {
    int[][] matrix = new int[rows][columns];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        matrix[i][j] = (int) (Math.random() * 10);
      }
    }
    return matrix;
  }
}
