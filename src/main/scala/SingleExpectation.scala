package com.borachio

class SingleExpectation(
    val target: AnyRef,
    expectedArguments: Option[Product] = None, 
    returnValue: Option[Any] = None, 
    count: Option[Int] = None)
  extends Expectation {
  
  override def withArguments(arguments: Product) = {
    require(!expectedArguments.isDefined, "arguments can only be set once")
    new SingleExpectation(target, Some(arguments), returnValue, count)
  }
  
  override def returning(value: Any) = {
    require(!returnValue.isDefined, "return value can only be set once")
    new SingleExpectation(target, expectedArguments, Some(value), count)
  }
  
  override def times(n: Int) = {
    require(!count.isDefined, "count can only be set once")
    new SingleExpectation(target, expectedArguments, returnValue, Some(n))
  }
  
  override def then = new OrderedExpectations(List(this, new SingleExpectation(target)))
}
