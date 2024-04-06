public class GetMethods {
  public static void main(String[] args) {
    NativeMethods nativeMethods = new NativeMethods();
    // Строка
    System.out.println("Hello world length is: " + nativeMethods.getStringLength("Hello world!"));

    // Демонстрация вызова нативного метода для подсчета длины строки
    String testStr = "Hello JNI!";
    nativeMethods.callObjectMethod(testStr);

    // Переопределение поля
    Person person = new Person(25);
    System.out.println("Before age was changed: " + person.age);
    nativeMethods.changeObjectField(person);
    System.out.println("After age was changed: " + person.age);
  }
}
