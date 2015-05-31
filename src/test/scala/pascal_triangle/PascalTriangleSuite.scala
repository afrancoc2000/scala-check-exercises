package pascal_triangle

import org.scalacheck.Prop.{BooleanOperators, forAll}
import org.scalacheck.{Gen, Properties}
import pascal_triangle.PascalTriangle._

object PascalTriangleProperties extends Properties("Pascal Triangle") {

  val validNumbers = for (n <- Gen.choose(0, 1000)) yield n

  property("El primer valor en todas las filas siempre es 1") = forAll(validNumbers){
    (row: Int) => pascal(0, row) == 1
  }

  property("El ultimo valor en todas las filas siempre es 1") = forAll(validNumbers){
    (row: Int) => pascal(row, row) == 1
  }

  property("Cualquier valor fuera del triangulo es 0") = forAll{(col:Int, row: Int) =>
    (col < 0 || col > row) ==> (pascal(col, row) == 0)
  }

  property("Cualquier valor dentro del triangulo es igual a la suma de sus 2 valores superiores I") =
    forAll{(col: Int, row: Int) =>
      (row > 0 && row < 20 && col >= 0 && col <= row) ==>
      (pascal(col, row) == pascal(col - 1, row - 1) + pascal(col, row - 1))
  }

  val genPosition: Gen[(Int, Int)] = for {
    row <- Gen.choose(1, 20)
    col <- Gen.choose(0, row)
  } yield (col, row)

  property("Cualquier valor dentro del triangulo es igual a la suma de sus 2 valores superiores II") = forAll(genPosition){
      pos: (Int, Int) => pascal(pos._1, pos._2) == pascal(pos._1 - 1, pos._2 - 1) + pascal(pos._1, pos._2 - 1)
  }

}
