package example;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@ToString
public class Order {

  private final OrderId id;

  private OrderStatus status;
  private List<OrderLine> lines = new ArrayList<>();

  Order(OrderId id) {
    this(id, OrderStatus.NEW);
  }

  Order(OrderId id, OrderStatus status) {
    this.id = Objects.requireNonNull(id);
    this.status = Objects.requireNonNull(status);
  }

  public OrderId getId() {
    return id;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public List<OrderLine> getLines() {
    return Collections.unmodifiableList(lines);
  }

  public void open() {
    if (!status.equals(OrderStatus.NEW)) {
      throw new IllegalOrderStatusException(this, OrderStatus.NEW);
    }

    this.status = OrderStatus.OPENED;
  }

  public void close() {
    if (!status.equals(OrderStatus.OPENED)) {
      throw new IllegalOrderStatusException(this, OrderStatus.OPENED);
    }

    this.status = OrderStatus.CLOSED;
  }

  public void addLine(ProductId productId, Quantity quantity, Money unitPrice) {
    if (!status.equals(OrderStatus.NEW)) {
      throw new IllegalOrderStatusException(this, OrderStatus.NEW);
    }

    lines.add(new OrderLine(productId, quantity, unitPrice));
  }

  public void removeLine(final ProductId productId) {
    if (!status.equals(OrderStatus.NEW)) {
      throw new IllegalOrderStatusException(this, OrderStatus.NEW);
    }

    Iterator<OrderLine> linesIterator = lines.iterator();
    while (linesIterator.hasNext()) {
      OrderLine line = linesIterator.next();
      if (line.getProductId().equals(productId)) {
        linesIterator.remove();
      }
    }
  }


  public Money calculateTotal() {
    // TODO
    return null;
  }
}
