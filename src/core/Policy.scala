package core

trait Policy {
  def select_action(state: State):  Action
}

