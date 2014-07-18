package example

class IllegalOrderStatusException extends RuntimeException{

  Order order
  OrderStatus expectedStatus

  @Override
  String getMessage() {
    "Illegal state of order with id: '$order.id', status was: '$order.status', expected status: '$expectedStatus'."
  }

}
