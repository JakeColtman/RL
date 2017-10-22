package TwoBandit
import core._
import org.scalatest.{FlatSpec, Matchers}

trait Bandit {
  def pull : Double
}

case class UniformBandit(a: Double, b: Double) extends Bandit {
  def pull : Double = {
    val r = scala.util.Random
    a + (b - a) * r.nextDouble()
  }
}

case object BanditFactory{
  def uniform(a: Double, b: Double): Bandit = UniformBandit(a, b)
}

class UniformBanditSpec extends FlatSpec with Matchers {
  "A uniform bandit " should " return a reward between its min and max" in {
    val reward = BanditFactory.uniform(10.0, 16.0).pull
    reward should be (13.0 +- 3.0)
  }

  "A uniform bandit " should " not return the same value on repeat draws" in {
    val bandit = BanditFactory.uniform(10.0, 16.0)
    val rewards = List.range(0, 100).map(_ => bandit.pull)
    val mean = rewards.sum / rewards.size
    val squared_diff_from_mean = rewards.map(x => x - mean)
    squared_diff_from_mean should not be 0.0
  }

  "The mean of rewards from a uniform bandit " should " converge to the mean of the distr " in {
    val bandit = BanditFactory.uniform(10.0, 16.0)
    val rewards = List.range(0, 100).map(_ => bandit.pull)
    val mean = rewards.sum / rewards.size
    mean should be (13.0 +- 0.5)
  }
}

