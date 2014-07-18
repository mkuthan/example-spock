package example;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Quantity {

  @Getter
  int value;

  public Quantity(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than zero but was: " + value + ".");
    }
    this.value = value;
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }
}
