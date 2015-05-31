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
    nombre    <- Gen.oneOf("Ana", "Ana María", "Carlos", "Carlos Alberto", "Claudia Patricia", "Juan", "Luis",
      "Luis Alberto", "Luis Eduardo", "Luz", "Luz Marina", "María del Carmen", "Rosa", "Sandra", "Jorge", "José",
      "Juan Carlos", "Luis Fernando", "María", "María Elena", "Santiago", "Valentina")

    apellido  <- Gen.oneOf("Días", "García", "Gómez", "González", "Hernández", "Jiménez", "López", "Martínez",
      "Moreno", "Muñoz", "Pérez", "Ramírez", "Rodríguez", "Rojas", "Sánchez")

    edad      <- Gen.choose(0, 100)
    genero    <- Gen.oneOf(Some("masculino"), Some("femenino"), None)
  }yield Usuario(id = id, nombre = nombre, apellido, edad, genero)
}

val usr = usuariosGen.sample

