package matrix.factorization

import scala.collection.mutable

package object types {

  // Types needed by matrix factorization
  type UserId = Int
  type ItemId = Int
  type TopK = mutable.PriorityQueue[Prediction]

  
  case class Recommendation(targetId: ItemId, topK: List[ItemId], evaluationId: Long, timestamp: Long)

  /**
    * Important properties to have:
    *   Always find (id, vector) pairs
    *   Always descending order
    * @param id
    */
 case class ItemVector(id: ItemId, vector: Vector) extends Ordered[ItemVector] {
    self =>
    override def compare(that: ItemVector): Int =
      if(self.vector.normSqr == that.vector.normSqr)
        self.id compare that.id
      else
        -(self.vector.normSqr compare that.vector.normSqr)
  }

  /**
    * import matrix.factorization.properties to have:
    *   always descending on score
    * @param itemId
    * @param score
    */
  case class Prediction(itemId: ItemId, score: Double) extends Ordered[Prediction]{ self =>

    import scala.math.Ordered.orderingToOrdered

    def compare(that: Prediction): Int =
          -(self.score compare that.score)
  }

  def createTopK: TopK =
    new mutable.PriorityQueue[Prediction]()

}