package core

trait Environment {
  def act(action: Action) : Outcome
  def state : State
}

case class MockEnvironment() extends Environment {
  override def act(action: Action): Outcome = Outcome(this, 5.0)

  override def state: State = MockState()
}