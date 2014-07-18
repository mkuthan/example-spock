package example;

public class OrderFactory {

  private int orderNumberCounter = 1;

  public Order createOrder() {
    OrderId id = new OrderId(Integer.toString(orderNumberCounter++));
    return new Order(id);
  }
}
