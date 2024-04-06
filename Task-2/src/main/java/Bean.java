import java.util.ArrayList;

public class Bean {
  private final String name;
  private final byte[] byteArray;
  private final ArrayList<Integer> arrayList;
  private final int primitive;
  private Bean selfReference;

  public Bean(String name) {
    this.name = name;
    this.byteArray = new byte[16];
    this.arrayList = new ArrayList<>();
    this.primitive = 0;
    this.selfReference = this;
  }

  public void setSelfReference(Bean bean) {
    this.selfReference = bean;
  }
}