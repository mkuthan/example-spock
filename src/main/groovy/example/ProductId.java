package example;

import lombok.NonNull;
import lombok.Value;

@Value
public class ProductId {

  @NonNull
  String id;

  @Override
  public String toString() {
    return id;
  }

}
