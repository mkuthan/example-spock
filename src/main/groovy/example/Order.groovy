package example

import groovy.transform.Canonical
import groovy.transform.ToString

@ToString
class Order {
  OrderStatus status = OrderStatus.NEW

  OrderId id
  List<OrderLine> lines = new ArrayList<>()

  void open() {
    if (status != OrderStatus.NEW) {
      throw IllegalArgumentException('Cannot open order in status ' + status)
    }
    this.status = OrderStatus.OPENED
  }

  void close() {
    if (status != OrderStatus.OPENED) {
      throw IllegalArgumentException('Cannot close order in status' + status)
    }
    this.status = OrderStatus.CLOSED
  }

  void addLine(ProductId productId, Integer quantity, Money unitPrice) {
    if (status != OrderStatus.NEW) {
      throw IllegalArgumentException('Cannot add line to order in status' + status)
    }
    lines.add(new OrderLine(productId, quantity, unitPrice))
  }

  void removeLine(ProductId productId) {
    if (status != OrderStatus.NEW) {
      throw IllegalArgumentException('Cannot remove line from order in status' + status)
    }
    lines.removeAll {it.productId.equals(productId)}
  }

}
