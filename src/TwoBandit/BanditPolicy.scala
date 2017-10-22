package TwoBandit
import core._


case class AlwaysLeftBanditPolicy() extends Policy {
  override def select_action(state: State): Action = Left()
}

case class RandomPolicy() extends Policy {
  override def select_action(state: State): Action = {
    val r = scala.util.Random
    if (r.nextDouble() < 0.5) Left()
    else Right()
  }
}

object EvaluateRandom extends App {
  val left_b = BanditFactory.uniform(3.0, 10.0)
  val right_b = BanditFactory.uniform(1.0, 10.0)
  val env = BanditEnvironment(left_b, right_b)
  val policy = RandomPolicy()
  val policyevaluator: PolicyEvaluator = new PolicyEvaluator()
  val policy_value = policyevaluator.evaluate(env, policy, 10000)
  println(policy_value)
}

object EvaluateAlwaysLeft extends App {
  val left_b = BanditFactory.uniform(3.0, 10.0)
  val right_b = BanditFactory.uniform(1.0, 10.0)
  val env = BanditEnvironment(left_b, right_b)
  val policy = AlwaysLeftBanditPolicy()
  val policyevaluator: PolicyEvaluator = new PolicyEvaluator()
  val policy_value = policyevaluator.evaluate(env, policy, 100)
  println(policy_value)
}