package core

class PolicyEvaluator {
  def evaluate(environment: Environment, policy: Policy, n_iter: Int) : Double = {
    9.0
//    var rewards = List[Double]()
//    var env = environment
//    var period_reward = 0.0
//    for (x <- List.range(0, n_iter)){
//      val action = policy(env.state)
//      val response = env.act(action)
//      period_reward = response._1
//      env = response._2
//      rewards = rewards ++ List[Double](period_reward)
//      policy.update(env.state, action, period_reward)
//    }
//    rewards.sum / rewards.size
  }
}
