import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Класс для перовой части задания.
 */
public class UtilityClass {

  /**
   * Метод для возвращения длины строки
   *
   * @param inputString строка
   * @return длина строки
   */
  public int getStringLength(String inputString) {
    if (inputString != null) {
      return inputString.length();
    } else {
      return 0;
    }
  }

  /**
   * Метод для вызова метода у переданного объекта и возвращения его значения.
   * Предполагается, что метод не принимает параметров и возвращает значение типа String.
   * Требует обработки исключений, например, если метод не существует.
   *
   * @param object     объект
   * @param methodName имя метода
   * @return значение типа String
   */
  public String callMethodOnObject(Object object, String methodName) {
    try {
      Method method = object.getClass().getMethod(methodName);
      return (String) method.invoke(object); // приведение типа может потребовать дополнительной проверки
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Метод для изменения значения java-поля объекта.
   * Предполагается, что поле имеет тип String.
   * Требует обработки исключений, например, если поле не существует.
   *
   * @param object    объект
   * @param fieldName название поля
   * @param newValue  новое знаяение
   */
  public void changeObjectField(Object object, String fieldName, String newValue) {
    try {
      Field field = object.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      field.set(object, newValue);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

