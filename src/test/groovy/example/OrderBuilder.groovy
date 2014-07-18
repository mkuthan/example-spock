package example

import groovy.transform.TypeChecked

@TypeChecked
class OrderBuilder {

  OrderId id = new OrderId("any id");
  OrderStatus status = OrderStatus.NEW
  List<OrderLine> lines = []

  OrderBuilder withStatus(OrderStatus status) {
    this.status = status
    this
  }

  OrderBuilder newOrder() {
    withStatus(OrderStatus.NEW)
  }

  OrderBuilder openedOrder() {
    withStatus(OrderStatus.OPENED)
  }

  OrderBuilder withLines(List<OrderLine> lines) {
    this.lines = lines
    this
  }

  OrderBuilder withDefaultLines(List<ProductId> productIds) {
    withLines(productIds.collect { productId -> new OrderLine(productId, new Quantity(1), new Money(1.0, "EUR")) })
  }

  OrderBuilder withLine(OrderLine line) {
    withLines([line])
  }

  OrderBuilder withDefaultLine(ProductId productId) {
    withDefaultLines([productId])
  }

  Order build() {
    new Order(id, status)
  }

}
