package example;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
public class OrderLine {

  @Getter
  private final ProductId productId;

  @Getter
  private final Quantity quantity;

  @Getter
  private final Money unitPrice;

  public OrderLine(ProductId productId, Quantity quantity, Money unitPrice) {
    this.productId = Objects.requireNonNull(productId);
    this.quantity = Objects.requireNonNull(quantity);
    this.unitPrice = Objects.requireNonNull(unitPrice);
  }
}
