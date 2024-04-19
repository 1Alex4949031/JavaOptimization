import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс со значением value.
 */
class ValueClass {
  int value;

  public ValueClass(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return Integer.toString(this.value);
  }
}

public class Main {
  public static void main(String[] args) {
    List<ValueClass> list = new ArrayList<>();
    Random random = new Random();

    // Заполнение списка 10 случайными объектами ValueClass
    for (int i = 0; i < 10; i++) {
      list.add(new ValueClass(random.nextInt(100)));
    }

    // Печать списка до сортировки
    System.out.println("Before sorting:");
    printList(list);

    // Сортировка списка методом пузырька
    bubbleSort(list);

    // Печать списка после сортировки
    System.out.println("After sorting:");
    printList(list);
  }

  /**
   * Метод для ручной сортировки списка объектов ValueClass методом пузырька.
   *
   * @param list список для сортировки
   */
  public static void bubbleSort(List<ValueClass> list) {
    boolean swapped = true;
    while (swapped) {
      swapped = false;
      for (int i = 1; i < list.size(); i++) {
        if (list.get(i - 1).value > list.get(i).value) {
          // Обмен элементов местами
          ValueClass temp = list.get(i);
          list.set(i, list.get(i - 1));
          list.set(i - 1, temp);
          swapped = true;
        }
      }
    }
  }

  /**
   * Метод для печати списка.
   *
   * @param list список для печати
   */
  public static void printList(List<ValueClass> list) {
    list.forEach(i ->
        System.out.print(i + " ")
    );
    System.out.println();
  }
}
