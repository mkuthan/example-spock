package example

import groovy.transform.AnnotationCollector
import groovy.transform.ToString
import groovy.transform.TupleConstructor

@TupleConstructor
@ToString(includePackage = false, includeNames = true)
@AnnotationCollector()
public @interface Entity {
}