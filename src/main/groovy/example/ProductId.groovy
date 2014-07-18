package example

@ValueObject
class ProductId {

  String id

  @Override
  String toString() {
    id
  }
}
