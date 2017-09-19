package puzzle.objects

class Square(val leftUp: Int, val rightUp: Int, val leftDown: Int, val rightDown: Int) {

  def toFormattedString: String = s"$leftUp $rightUp $leftDown $rightDown${System.lineSeparator}"
}

object Square {

  def apply(s: String): Square = {

    val values = s.split(" ").map(s => s.toInt)

    require(values.length == 4)

    new Square(values(0), values(1), values(2), values(3))
  }

}
