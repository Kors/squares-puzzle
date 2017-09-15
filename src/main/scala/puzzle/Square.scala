package puzzle

case class Square(leftUp: Int, rightUp: Int, leftDown: Int, rightDown: Int) {
  def toFormattedString: String = {
    leftUp + " " + rightUp + " " + leftDown + " " + rightDown + System.lineSeparator()
  }
}

object SquareFactory {
  def apply(s: String): Square = {
    val values = s.split(" ")
    assert(values.size == 4)
    Square(values(0).toInt, values(1).toInt, values(2).toInt, values(3).toInt)
  }
}