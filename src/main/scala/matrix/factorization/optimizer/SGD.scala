package matrix.factorization.optimizer
import matrix.factorization.types.Vector

class SGD(learningRate: Double, lambda: Double, normalizationThreshold: Double) extends Optimizer {

    override def delta(rating: Double, user: Array[Double], item: Array[Double]): (Array[Double], Array[Double]) = {

//      println("SGD########")
//      println("rating: " + rating + ", user: " + Vector(user).toString + ", item: " + Vector(item).toString)
//      println("########SGD")

      val e = rating - user.zip(item).map { case (x, y) => x * y }.sum
//      val normUser = Math.sqrt(item.map(i => e * e * i *i).sum)
      //      1 v 0.5
//      val normalizationUser = if (normUser > t) {
//        t / normUser
//      } else 1

      val normalizationUser = Math.sqrt(item.map(i => e * e * i *i).sum) match {
        case x if x > normalizationThreshold => normalizationThreshold / x
        case _ => 1
      }
      val normalizationItem = Math.sqrt(user.map(i => e * e * i *i).sum) match {
        case x if x > normalizationThreshold => normalizationThreshold / x
        case _ => 1
      }

      ((item.map(i => learningRate * e * i * normalizationUser), user.map(lambda * _)).zipped.map(_-_),
        (user.map(u => learningRate * e * u * normalizationItem), item.map(lambda * _)).zipped.map(_-_))
  }
}