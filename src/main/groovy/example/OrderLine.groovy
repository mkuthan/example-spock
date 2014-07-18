package example

import groovy.transform.Canonical

@Canonical
class OrderLine {
  ProductId productId
  Integer quantity
  Money unitPrice
}
