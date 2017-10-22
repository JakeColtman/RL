package TwoBandit
import core._
import org.scalatest.{FlatSpec, Matchers}

case class BanditEnvironment(left_bandit: Bandit, right_bandit: Bandit) extends Environment {
  override def act(action: Action): Outcome = {
    action match {
      case _ : Left => Outcome(this, left_bandit.pull)
      case _ : Right => Outcome(this, left_bandit.pull)
    }
  }
  def state: State = BanditState

  override def possible_actions: List[Action] = List(Left(), Right())
}

class BanditEnvironmentSpec extends FlatSpec with Matchers {
  "An environment " should " return a reward for all actions " in {
    val left_b = BanditFactory.uniform(1.0, 10.0)
    val right_b = BanditFactory.uniform(1.0, 10.0)
    val env = BanditEnvironment(left_b, right_b)
    env.act(Left()).reward should be (5.0 +- 5.0)
    env.act(Right()).reward should be (5.0 +- 5.0)
  }

  "An environment " should " not change state when acted on " in {
    val left_b = BanditFactory.uniform(1.0, 10.0)
    val right_b = BanditFactory.uniform(1.0, 10.0)
    val env = BanditEnvironment(left_b, right_b)
    env.act(Left()).environment should be (env)
  }
}


