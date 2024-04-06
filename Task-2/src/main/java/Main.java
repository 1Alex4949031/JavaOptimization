import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Bean bean1 = new Bean("Bean example 1");
    Bean bean2 = new Bean("Bean example 2");
    Bean bean3 = new Bean("Bean example 3");
    bean1.setSelfReference(bean2);
    bean2.setSelfReference(bean3);
    bean3.setSelfReference(bean1);

    boolean startBadThread = false;
    for (String arg : args) {
      if (arg.equals("--bad")) {
        startBadThread = true;
        break;
      }
    }

    for (int i = 0; i < 5; i++) {
      new Thread(() -> {
        try {
          Singleton singleton = Singleton.getInstance();
          Bean bean = new Bean("Bean " + Thread.currentThread().getId());
          Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException ignored) {
        }
      }).start();
    }

    if (startBadThread) {
      new Thread(() -> {
        List<Object> objectList = new ArrayList<>();
        while (true) {
          objectList.add(new Object());
        }
      }).start();
    }
  }
}
