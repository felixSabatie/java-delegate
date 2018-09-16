package delegated;

import java.io.Serializable;

public class Calculator implements Serializable {

  public int add(int a, int b) {
    return a + b;
  }

  public int substract(int a, int b) {
    return a - b;
  }

}
