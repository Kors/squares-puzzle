package puzzle

case class SquareOfSquares(leftUpSquare: Square, rightUpSquare: Square, leftDownSquare: Square, rightDownSquare: Square) {
  private val sum = 10
  val isCorrect: Boolean = {
    if (leftUpSquare.rightDown + rightUpSquare.leftDown + leftDownSquare.rightUp + rightDownSquare.leftUp == sum &&
      leftUpSquare.rightUp + rightUpSquare.leftUp <= sum &&
      leftUpSquare.leftDown + leftDownSquare.leftUp <= sum &&
      rightUpSquare.rightDown + rightDownSquare.rightUp <= sum &&
      leftDownSquare.rightDown + rightDownSquare.leftDown <= sum)
      true
    false
  }
}
