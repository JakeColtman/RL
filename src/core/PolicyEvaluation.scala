package core

trait PolicyEvaluation{
  def evaluate(policy: Policy, reward_memory: Memory, state_memory: Memory): StateValue
}

case class StateValue(default : Double = 0.0) {
  var states_value_map = Map[State, Double]()
  def update_value(state: State, value: Double) : Unit = {
    states_value_map = states_value_map ++ Map(state->value)
  }
  def value(state: State): Double = states_value_map.getOrElse(state, default)
}

case object StateValueFactory {
  def apply(default: Double = 0.0) = StateValue(default)
}

class IterativePolicyEvaluation(environment: Environment, theta: Double) extends PolicyEvaluation {

  def update_state_estimate(policy: Policy, possible_actions: List[Action], current_value: Double, state: State, reward_memory: Memory, state_memory: Memory) : Double = {
    val action_probs = policy.assign_probabilities(state, environment.possible_actions(state))
    for (a <- action_probs) {
      for (s <- state_memory.value_of(state, a)) {
        for (r <- reward_memory.value_of())
      }
    }
  }

  def evaluate(policy: Policy, reward_memory: Memory, state_memory: Memory, possible_states: List[State]): StateValue = {
    val stateValue = StateValueFactory()
    var delta = theta + 1
    while(delta > theta) {
      delta = 0.0
      for (s <- possible_states){
        val current_state_value = stateValue.value(s)
        val new_state_value =
    }
  }
}
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
