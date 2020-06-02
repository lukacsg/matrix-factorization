package matrix.factorization.types

import org.slf4j.LoggerFactory

case class Vector(value: Array[Double]) extends Serializable with Parameter {
  lazy val normSqr: Double = Vector.vectorLengthSqr(this)
  lazy val norm: Double = math.sqrt(Vector.vectorLengthSqr(this))


  def dotProduct(other: Vector): Double =
    Vector.dotProduct(this, other)

  def addition(other: Vector): Vector =
    Vector.vectorSum(this, other)

  override def toString: String =
    value.tail.foldLeft(value.head.toString)((acc, c) => acc + "," + c.toString)

  override def equals(o: Any): Boolean = {
    o match {
      case o: Vector => o.value sameElements value
      case _ => false
    }
  }
}

object Vector {
  private val log = LoggerFactory.getLogger(classOf[Vector])

  def apply(n: Int): Vector = new Vector(Array.ofDim[Double](n))
  /**
    * Returns the squared length of a vector
    *
    * @param v
    * The input vector
    * @return
    * The squared length of v
    */
  def vectorLengthSqr(v: Vector): Double =
    vectorLengthSqr(v.value)

  def vectorLengthSqr(v: Array[Double]): Double = {
    var res = 0.0
    var i = 0
    val n = v.length
    while (i < n) {
      res += v(i) * v(i)
      i += 1
    }
    res
  }

  /**
    * Returns the dot product of two vectors
    *
    * @param u
    * The first vector
    * @param v
    * The second vector
    * @return
    * The dot product of u and v
    */
  def dotProduct(u: Vector, v: Vector): Double =
    dotProduct(u.value, v.value)

  def dotProduct(u: Array[Double], v: Array[Double]): Double = {
    var res = 0.0
    var i = 0
    val n = u.length  // assuming u and v have the same number of factors
    while (i < n) {
      res += u(i) * v(i)
      i += 1
    }
    res
  }

  /**
    * Returns the sum of two vectors
    *
    * @param u
    * The first vector
    * @param v
    * The second vector
    * @return
    * The sum off u and v
    * @throws FactorIsNotANumberException
    * If the operation results in a vector with a NaN coordinate
    */
  def vectorSum(u: Vector, v: Vector): Vector =
    Vector(vectorSum(u.value, v.value))

  def vectorSum(u: Array[Double], v: Array[Double]): Array[Double] = {
    val n = u.length
    val res = new Array[Double](n)
    var i = 0
    while (i < n) {
      res(i) = u(i) + v(i)
      if (res(i).isNaN) {
        println("u:" + Vector(u).toString)
        println("v:" + Vector(v).toString)
//        log.info("u:" + u)
//        log.info("v:" + v)
        throw new FactorIsNotANumberException
      }
      i += 1
    }
    res
  }

  /**
    * Exception to be thrown when a vector addition results in a NaN
    */
  class FactorIsNotANumberException extends Exception
}