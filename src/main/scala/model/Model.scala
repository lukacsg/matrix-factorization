package model

/**
 Representation of a machine learning model, where 
   K is the type of the key of the parameters
   P is the type of the parameters
   T is the type of the prediction
 */
abstract class Model[K, P, T] {

  /**
    Stores the parameters for the given model
   */
  val model: ModelState[K,P]

}