package example

import spock.lang.Specification
import spock.lang.Unroll

class OrderSpec extends Specification {

  def "should open order"() {
    given:
    def order = new OrderBuilder().newOrder().build()

    when:
    order.open()

    then:
    order.getStatus() == OrderStatus.OPENED
  }

  def "should close order"() {
    given:
    def order = new OrderBuilder().openedOrder().build()

    when:
    order.close()

    then:
    order.getStatus() == OrderStatus.CLOSED
  }

  def "should add new order line to empty order"() {
    given:
    def newLineProductId = new ProductId("newId")
    def newLineQuantity = new Quantity(3)
    def newLineUnitPrice = new Money(10.10, "EUR")

    def order = new OrderBuilder()
        .newOrder()
        .withLine(new OrderLine(newLineProductId, newLineQuantity, newLineUnitPrice))
        .build()

    when:
    order.addLine(newLineProductId, newLineQuantity, newLineUnitPrice)

    then:
    order.getLines().last().getProductId() == newLineProductId
    order.getLines().last().getQuantity() == newLineQuantity
    order.getLines().last().getUnitPrice() == newLineUnitPrice
  }

  @Unroll
  def "should not add order line when order status is #status"(OrderStatus status) {
    given:
    def order = new OrderBuilder().withStatus(status).build()

    when:
    order.addLine(new ProductId("any id"), new Quantity(1), new Money(1.0, "EUR"))

    then:
    thrown(IllegalOrderStatusException)

    where:
    status << [OrderStatus.OPENED, OrderStatus.CLOSED]
  }

  def "should remove existing order line"() {
    given:
    def productId1 = new ProductId("id1")
    def productId2 = new ProductId("id2")
    def productId3 = new ProductId("id3")

    def order = new OrderBuilder()
        .newOrder()
        .withDefaultLines([productId1, productId2, productId3])
        .build()

    when:
    order.removeLine(productId2)

    then:
    order.getLines().every { it.getProductId() != productId2 }
  }

  @Unroll
  def "should not remove order line when order status is #status"(OrderStatus status) {
    given:
    def productId = new ProductId("id")

    def order = new OrderBuilder()
        .withStatus(status)
        .withDefaultLine(productId)
        .build()

    when:
    order.removeLine(productId)

    then:
    thrown(IllegalOrderStatusException)

    where:
    status << [OrderStatus.OPENED, OrderStatus.CLOSED]
  }

}