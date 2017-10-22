package core

import org.scalatest.{FlatSpec, Matchers}

trait Memory {
  def observe_outcome(state: State, action: Action, outcome: Outcome): Unit
  def value_of(state: State, action: Action): Double
}

trait LookupMemory extends Memory  {
  var lookup: Map[(State, Action), (Double, Int)]
  val default: Double
  val default_weight: Int
  def value_of(state: State, action: Action): Double = {
    lookup.getOrElse((state, action), (default, default_weight))._1
  }}

case class MeanObservedLookupMemory(default: Double, default_weight: Int = 0) extends LookupMemory {
  var lookup : Map[(State, Action), (Double, Int)] = Map[(State, Action), (Double, Int)]()

  override def observe_outcome(state: State, action: Action, outcome: Outcome): Unit = {
    val cur = lookup.getOrElse((state, action), (default, default_weight))
    val n = cur._2 + 1
    val new_value = cur._1 + ((outcome.reward - cur._1) / n)
    lookup = lookup ++ Map((state, action)->(new_value, n))
  }
}

case class RecencyWeightedLookupMemory(default: Double, default_weight: Int = 0) extends LookupMemory {
  var lookup : Map[(State, Action), (Double, Int)] = Map[(State, Action), (Double, Int)]()

  override def observe_outcome(state: State, action: Action, outcome: Outcome): Unit = {
    val cur = lookup.getOrElse((state, action), (default, default_weight))
    val new_value = cur._1 + ((outcome.reward - cur._1) * cur._2)
    lookup = lookup ++ Map((state, action)->(new_value, cur._2))
  }
}

class MeanObservedLookupSpec extends FlatSpec with Matchers {
  "The lookup " should " return the default for unseen state action pairs " in {
    MeanObservedLookupMemory(10.0).value_of(MockState(), MockAction()) should be (10.0)
  }

  "The lookup " should " return the only observed value if one observation" in {
    val lookup = MeanObservedLookupMemory(10.0)
    lookup.observe_outcome(MockState(), MockAction(), Outcome(MockEnvironment(), 3.0))
    lookup.value_of(MockState(), MockAction()) should be (3.0)
  }

  "The lookup " should " accurately record the observed mean " in {
    val lookup = MeanObservedLookupMemory(10.0)
    lookup.observe_outcome(MockState(), MockAction(), Outcome(MockEnvironment(), 3.0))
    lookup.observe_outcome(MockState(), MockAction(), Outcome(MockEnvironment(), 6.0))
    lookup.observe_outcome(MockState(), MockAction(), Outcome(MockEnvironment(), 9.0))
    lookup.value_of(MockState(), MockAction()) should be (6.0)
  }
}