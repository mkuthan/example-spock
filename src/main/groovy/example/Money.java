package example;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class Money {

  @NonNull
  BigDecimal amount;

  @NonNull
  String currency;

  @Override
  public String toString() {
    return amount + " " + currency;
  }


  public Money add(Money other) {
    if (!currency.equals(other.getCurrency())) {
      throw new IllegalArgumentException("Currency does not match, expected: " + currency + " but was " + other
          .getCurrency() + ".");
    }

    return new Money(amount.add(other.getAmount()), currency);
  }
}
