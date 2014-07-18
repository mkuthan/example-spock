package example

class ObjectMother {

  static def populate(object) {
    def filtered = object.metaClass.properties.findAll { !(it.name == 'class' || it.name == 'metaClass')}

    filtered.each {
      if(object."${it.name}" == null) {
        object."${it.name}" = createValue(it)
      }
    }

    return object
  }

  private static def createValue(field) {
    switch (field.type) {
      case boolean:
        return true
      case byte:
        return 1
      case short:
        return 1
      case int:
        return 1
      case long:
        return 1
      case float:
        return 1
      case double:
        return 1.0
      case Boolean:
        return Boolean.TRUE
      case Byte:
        return 1
      case Integer:
        return 1
      case Long:
        return 1
      case Float:
        return 1
      case Double:
        return 1
      case String:
        return "any $field.name"
      case BigDecimal:
        return BigDecimal.ZERO
      default:
        return field.type.newInstance().populate()
    }
  }
}