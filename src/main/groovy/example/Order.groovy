package example

@Entity
class Order {

  OrderId id

  OrderStatus status = OrderStatus.NEW
  List<OrderLine> lines = new ArrayList<>()

  void open() {
    if (status != OrderStatus.NEW) {
      throw new IllegalOrderStatusException(order: this, expectedStatus: OrderStatus.NEW)
    }
    this.status = OrderStatus.OPENED
  }

  void close() {
    if (status != OrderStatus.OPENED) {
      throw new IllegalOrderStatusException(order: this, expectedStatus: OrderStatus.OPENED)
    }
    this.status = OrderStatus.CLOSED
  }

  void addLine(ProductId productId, Integer quantity, Money unitPrice) {
    if (status != OrderStatus.NEW) {
      throw new IllegalOrderStatusException(order: this, expectedStatus: OrderStatus.NEW)
    }
    lines.add(new OrderLine(productId, quantity, unitPrice))
  }

  void removeLine(ProductId productId) {
    if (status != OrderStatus.NEW) {
      throw new IllegalOrderStatusException(order: this, expectedStatus: OrderStatus.NEW)
    }
    lines.removeAll {it.productId.equals(productId)}
  }

}
