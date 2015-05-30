package general_spec

import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

object GeneralSpecification extends Properties("General"){

  property("propConcatLists") = forAll { (l1: List[Int], l2: List[Int]) =>
    l1.size + l2.size == (l1 ::: l2).size
  }

  property("propSqrt") = forAll { (n: Int) => scala.math.sqrt(n*n) == n }
}
