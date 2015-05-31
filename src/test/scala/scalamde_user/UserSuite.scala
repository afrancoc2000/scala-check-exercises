package scalamde_user

import org.scalacheck.{Arbitrary, Gen, Properties}
import org.scalacheck.Prop.forAll


object UserProperties extends Properties("Propiedades de usuario"){

  val listaNombres = List("Ana", "Ana María", "Carlos", "Carlos Alberto", "Claudia Patricia", "Juan", "Luis",
    "Luis Alberto", "Luis Eduardo", "Luz", "Luz Marina", "María del Carmen", "Rosa", "Sandra", "Jorge", "José",
    "Juan Carlos", "Luis Fernando", "María", "María Elena", "Santiago", "Valentina")

  val listaApellidos = List("Días", "García", "Gómez", "González", "Hernández", "Jiménez", "López", "Martínez",
    "Moreno", "Muñoz", "Pérez", "Ramírez", "Rodríguez", "Rojas", "Sánchez")

  var idNo = 0

  val usuariosGen: Gen[Usuario] = {
    idNo += 1
    for{
      id        <- Gen.const(idNo)
      nombre    <- Gen.oneOf(listaNombres)
      apellido  <- Gen.oneOf(listaApellidos)
      edad      <- Gen.choose(0, 100)
      genero    <- Gen.oneOf(Some("masculino"), Some("femenino"), None)
    }yield Usuario(id = id, nombre = nombre, apellido, edad, genero)
  }

  implicit lazy val arbUsuario: Arbitrary[Usuario] = Arbitrary(usuariosGen)

  property("El tamaño del nombre completo es el tamaño del nombre + el tamaño del apellido + 1") =
    forAll{(usr: Usuario) =>
      usr.getNombreCompleto.length == usr.nombre.length + usr.apellido.length + 1
    }

  property("Un usuario mayor de de edad es todo el que tenga 18 o más años") =
    forAll{(usr: Usuario) =>
      usr.isMayorDeEdad == (usr.edad >= 18)
    }
}

