package puzzle

class Square(leftUp: Int, rightUp: Int, leftDown: Int, rightDown: Int)

object Square {
  def apply(s: String): Square = apply(s.toInt)

  def apply(value: Int): Square = {
    val leftUp: Int = value / 1000
    val rightUp: Int = (value / 100) % 10
    val leftDown: Int = (value / 10) % 100
    val rightDown: Int = value % 1000
    new Square(leftUp, rightUp, leftDown, rightDown)
  }
}