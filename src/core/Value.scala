package core

trait Value {
//  def state_memory: Memory
//  def reward_memory: Memory
  def observe_outcome(state: State, action: Action, outcome: Outcome) : Unit = {
//    reward_memory.observe_outcome(state, action, outcome)
//    state_memory.observe_outcome(state, action, outcome)
  }
}

//trait StateValue extends Value {
//  def value_of(policy: Policy, state: State): Double
//}
//
//trait ActionValue extends Value {
//  def value_of(policy: Policy, state: State, action: Action): Double = {
//
//  }
//}

trait RewardFunction extends Value {
  def value_of(state: State, action: Action){
    1.0
  }
}