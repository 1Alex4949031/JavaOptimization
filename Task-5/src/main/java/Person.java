/**
 * Класс Person.
 */
public class Person {
  public String firstname;
  public String lastname;

  public Person(String firstname, String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }
}
