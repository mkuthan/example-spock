package example

import spock.lang.Specification
import spock.lang.Unroll
import spock.util.mop.Use

class OrderSpec extends Specification {

  @Use(ObjectMother)
  def "should open order"() {
    given:
    Order order = new Order(status: OrderStatus.NEW).populate()

    when:
    order.open()

    then:
    order.status == OrderStatus.OPENED
  }

  @Use(ObjectMother)
  def "should close order"() {
    given:
    Order order = new Order(status: OrderStatus.OPENED).populate()

    when:
    order.close()

    then:
    order.status == OrderStatus.CLOSED
  }

  @Use(ObjectMother)
  def "should add new order line to empty order"() {
    given:
    Order order = new Order(status: OrderStatus.NEW).populate()

    ProductId newLineProductId = new ProductId("newId")
    Integer newLineQuantity = 3
    Money newLineUnitPrice = new Money(amount: 10.10, currency: "EUR")

    when:
    order.addLine(newLineProductId, newLineQuantity, newLineUnitPrice)

    then:
    order.lines.last().productId == newLineProductId
    order.lines.last().quantity == newLineQuantity
    order.lines.last().unitPrice == newLineUnitPrice
  }

  @Use(ObjectMother)
  @Unroll
  def "should not add order line when order status is #status"(OrderStatus status) {
    given:
    Order order = new Order(status: status).populate()

    when:
    order.addLine(new ProductId().populate(), 1, new Money().populate())

    then:
    thrown(IllegalOrderStatusException)

    where:
    status << [OrderStatus.OPENED, OrderStatus.CLOSED]
  }

  @Use(ObjectMother)
  def "should remove existing order line"() {
    given:
    ProductId productId1 = new ProductId(id: "id1")
    ProductId productId2 = new ProductId(id: "id2")
    ProductId productId3 = new ProductId(id: "id3")

    OrderLine orderLine1 = new OrderLine(productId: productId1).populate()
    OrderLine orderLine2 = new OrderLine(productId: productId2).populate()
    OrderLine orderLine3 = new OrderLine(productId: productId3).populate()

    Order order = new Order(status: OrderStatus.NEW, lines: [orderLine1, orderLine2, orderLine3]).populate()

    when:
    order.removeLine(productId2)

    then:
    order.lines.every {it.productId != productId2}
  }

  @Use(ObjectMother)
  @Unroll
  def "should not remove order line when order status is #status"(OrderStatus status) {
    given:
    ProductId productId = new ProductId(id: "id")
    Integer anyQuantity =  1
    Money anyUnitPrice = new Money().populate()

    Order order = new Order(status: status).populate()
    order.addLine(productId, anyQuantity, anyUnitPrice)

    when:
    order.removeLine(productId)

    then:
    thrown(IllegalOrderStatusException)

    where:
    status << [OrderStatus.OPENED, OrderStatus.CLOSED]
  }


}