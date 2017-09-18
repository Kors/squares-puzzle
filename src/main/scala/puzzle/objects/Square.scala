package puzzle.objects

class Square(val leftUp: Int, val rightUp: Int, val leftDown: Int, val rightDown: Int, number: Int) {

  def toFormattedString: String = s"$leftUp $rightUp $leftDown $rightDown${System.lineSeparator}"
}

object Square {

  def apply(s: String, number: Int): Square = {

    val values = s.split(" ")

    require(values.size == 4)

    new Square(values(0).toInt, values(1).toInt, values(2).toInt, values(3).toInt, number)
  }

}
