package core

trait Policy {
  def select_action(environment: Environment):  Action = {
    val r = scala.util.Random
    var cum_prob = 0
    val point_to_find = r.nextDouble()
    val probs = assign_probabilities(environment)
    var chosen_action = probs.head._1
    for (x <- probs) {
      cum_prob += x._2
      if (cum_prob > point_to_find) chosen_action = x._1
      scala.util.control.Breaks.break()
    }
    chosen_action
  }
  def assign_probabilities(environment: Environment): List[(Action, Double)] = {
    assign_probabilities(environment.state, environment.possible_actions)
  }
  def assign_probabilities(state: State, possible_actions: List[Action])
}

case class GreedyPolicy(valuation: Valuation) extends Policy {

  override def assign_probabilities(environment: Environment): List[(Action, Double)] = {
    val possible_actions = environment.possible_actions
    val action_values = possible_actions.map(a => (a, valuation.value_of(environment.state, a)))
    val max_value = action_values.map(x => x._2).max
    action_values.map(x => if (x._2 == max_value) (x._1, 1.0) else (x._1, 0.0))
  }

}

case class EGreedyPolicy(valuation: Valuation, e: Double) extends Policy {

  val greedy = GreedyPolicy(valuation)

  override def assign_probabilities(environment: Environment): List[(Action, Double)] = {
    val greedy_probs = greedy.assign_probabilities(environment)

    val reduced_best_action_by_e = greedy_probs.map(x => (x._1, if (x._2 == 1.0) 1.0 - e else 0.0))
    reduced_best_action_by_e.map(x => (x._1, x._2 + (e / greedy_probs.size)))
  }
}