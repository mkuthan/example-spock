package example

import groovy.transform.ToString

@ToString(includeNames = true)
class Employee {
  String firstName
  String lastName
  Integer age
  Department department
  Set<Employee> subordinates = new HashSet<>()
}
