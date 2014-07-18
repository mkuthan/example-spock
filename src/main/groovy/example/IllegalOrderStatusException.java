package example;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IllegalOrderStatusException extends RuntimeException {

  @Getter
  @NonNull
  final Order order;

  @Getter
  @NonNull
  final OrderStatus expectedStatus;

  @Override
  public String getMessage() {
    return "Illegal state of order with id: '" + order.getId() +
        "', status was: '" + order.getStatus() +
        "', expected status: '" + expectedStatus + "'.";
  }

}
