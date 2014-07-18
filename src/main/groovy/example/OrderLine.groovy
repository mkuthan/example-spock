package example

@Entity
class OrderLine {
  ProductId productId
  Integer quantity
  Money unitPrice
}
