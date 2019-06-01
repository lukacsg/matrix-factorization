package matrix.factorization.optimizer

class SGD(learningRate: Double) extends Optimizer {

    override def delta(rating: Double, user: Array[Double], item: Array[Double]): (Array[Double], Array[Double]) = {
      val e = rating - user.zip(item).map { case (x, y) => x * y }.sum

      (item.map(i => learningRate * e * i),
       user.map(u => learningRate * e * u))
  }
}