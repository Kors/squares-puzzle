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

object SquareOfSquares {
  private val sum = 10

  def apply(upPair: PairOfSquares, downPair: PairOfSquares): Option[SquareOfSquares] = {
    if (isCorrectForSquareOfSquares(upPair, downPair))
      Option(new SquareOfSquares(upPair.leftSquare, upPair.rightSquare, downPair.leftSquare, downPair.rightSquare))
    else
      Option.empty
  }

  private def isCorrectForSquareOfSquares(upPair: PairOfSquares, downPair: PairOfSquares): Boolean = {
    val set = Set(upPair.leftSquare, upPair.rightSquare, downPair.leftSquare, downPair.rightSquare)
    if (set.size == 4 &&
      upPair.leftSquare.rightDown + upPair.rightSquare.leftDown + downPair.leftSquare.rightUp + downPair.rightSquare.leftUp == sum &&
      upPair.leftSquare.leftDown + downPair.leftSquare.leftUp <= sum &&
      upPair.rightSquare.rightDown + downPair.rightSquare.rightUp <= sum
    )
      true
    else
      false
  }
}
