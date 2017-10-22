package core

trait Agent {
  def interact(environment: Environment): Environment
  def total_reward: Outcome
  def policy: Policy
}

//
//case class MyopicAgent(policy: Policy) extends Agent {
//  var total_reward = 0.0
//
//  override def interact(environment: Environment): Outcome = {
//    val action = policy.select_action(environment.state)
//    val outcome = environment.act(action)
//    total_reward += outcome.reward
//    outcome
//  }
//}
