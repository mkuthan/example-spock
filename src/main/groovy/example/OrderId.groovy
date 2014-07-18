package example

@ValueObject
class OrderId {

  String id

  @Override
  String toString() {
    id
  }
}
