package example

import groovy.transform.Canonical

@Canonical
class Money {
  BigDecimal amount
  String currency
}
