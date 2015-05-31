import org.scalacheck.{Gen, Arbitrary}
import Arbitrary._

import scalamde_user.Usuario

val myGen = for {
  n <- Gen.choose(10,20)
  m <- Gen.choose(2*n, 500)
} yield (n,m)
myGen.sample

val vowelGen1 = Gen.oneOf('A', 'E', 'I', 'O', 'U')
vowelGen1.sample

var idNo = 0

val usuariosGen: Gen[Usuario] ={
  idNo += 1
  for{
    id        <- Gen.const(idNo)
    nombre    <- Gen.oneOf("Ana", "Ana Mar�a", "Carlos", "Carlos Alberto", "Claudia Patricia", "Juan", "Luis",
      "Luis Alberto", "Luis Eduardo", "Luz", "Luz Marina", "Mar�a del Carmen", "Rosa", "Sandra", "Jorge", "Jos�",
      "Juan Carlos", "Luis Fernando", "Mar�a", "Mar�a Elena", "Santiago", "Valentina")

    apellido  <- Gen.oneOf("D�as", "Garc�a", "G�mez", "Gonz�lez", "Hern�ndez", "Jim�nez", "L�pez", "Mart�nez",
      "Moreno", "Mu�oz", "P�rez", "Ram�rez", "Rodr�guez", "Rojas", "S�nchez")

    edad      <- Gen.choose(0, 100)
    genero    <- Gen.oneOf(Some("masculino"), Some("femenino"), None)
  }yield Usuario(id = id, nombre = nombre, apellido, edad, genero)
}

val usr = usuariosGen.sample

