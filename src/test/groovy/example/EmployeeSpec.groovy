package example

import spock.lang.Specification
import spock.util.mop.Use

class EmployeeSpec extends Specification {

  @Use(ObjectMother)
  def "should build employee object with dependencies"() {
    given:
    Employee employee = new Employee(
        firstName: "John",
        lastName: "Smith"
    ).populate()

    expect:
    employee.firstName == "John"
    employee.lastName == "Smith"
    employee.age != null
    employee.department != null
    employee.department.name != null
    employee.subordinates != null

    println employee
  }

}