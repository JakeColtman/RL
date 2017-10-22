package core

trait Environment {
  def act(action: Action) : Outcome
  def state : State
  def possible_actions: List[Action]
  def possible_actions(state: State): List[Action]
}

case class MockEnvironment() extends Environment {
  override def act(action: Action): Outcome = Outcome(this, 5.0)
  override def state: State = MockState()
  override def possible_actions: List[Action] = List(MockAction())
}