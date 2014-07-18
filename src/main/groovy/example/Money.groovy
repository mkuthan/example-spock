package example

@ValueObject
class Money {

  BigDecimal amount
  String currency

  @Override
  String toString() {
    "$amount $currency"
  }
}
