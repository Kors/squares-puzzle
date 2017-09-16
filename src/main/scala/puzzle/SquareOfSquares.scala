package puzzle

case class SquareOfSquares(leftUpSquare: Square, rightUpSquare: Square, leftDownSquare: Square, rightDownSquare: Square) {
  def upSide: List[Square] = {
    List(leftUpSquare, rightUpSquare)
  }

  def downSide: List[Square] = {
    List(leftDownSquare, rightDownSquare)
  }

  def leftSide: List[Square] = {
    List(leftUpSquare, leftDownSquare)
  }

  def rightSide: List[Square] = {
    List(rightUpSquare, rightDownSquare)
  }
}

object SquareOfSquaresFactory {
  private val sum = 10

  def apply(leftUpSquare: Square, rightUpSquare: Square, leftDownSquare: Square, rightDownSquare: Square): Option[SquareOfSquares] = {
    if (isCorrectForSquareOfSquares(leftUpSquare, rightUpSquare, leftDownSquare, rightDownSquare))
      Option(SquareOfSquares(leftUpSquare, rightUpSquare, leftDownSquare, rightDownSquare))
    else
      Option.empty
  }

  private def isCorrectForSquareOfSquares(leftUpSquare: Square, rightUpSquare: Square, leftDownSquare: Square, rightDownSquare: Square): Boolean = {
    if (leftUpSquare.rightDown + rightUpSquare.leftDown + leftDownSquare.rightUp + rightDownSquare.leftUp == sum &&
      leftUpSquare.rightUp + rightUpSquare.leftUp <= sum &&
      leftUpSquare.leftDown + leftDownSquare.leftUp <= sum &&
      rightUpSquare.rightDown + rightDownSquare.rightUp <= sum &&
      leftDownSquare.rightDown + rightDownSquare.leftDown <= sum)
      true
    else
      false
  }
}
