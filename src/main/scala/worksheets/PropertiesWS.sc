import org.scalacheck._
import org.scalacheck.Prop.{forAll, BooleanOperators}

val propConcatLists =
  Prop.forAll { 	(l1: List[Int], l2: List[Int]) =>  l1.size + l2.size == (l1 ::: l2).size }
propConcatLists.check
val propSqrt = Prop.forAll { (n: Int) => scala.math.sqrt(n*n) == n }
propSqrt.check
val stringNumbers = Gen.oneOf("1", "2", "3")
val propStringNumbers = Prop.forAll(stringNumbers)(n => n.toInt.toString == n)

propStringNumbers.check

val propSmallInteger = Prop.forAll{n: Int =>
  (n >= 0 && n < 10000) ==> (n*2 >= 0 && n*2 <= 200)
}
propSmallInteger.check
