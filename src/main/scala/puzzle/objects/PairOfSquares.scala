package puzzle.objects

case class PairOfSquares(leftSquare: Square, rightSquare: Square)

object PairOfSquares {
  private val sum = 10

  def apply(leftSquare: Square, rightSquare: Square): Option[PairOfSquares] = {
    if (leftSquare.rightUp + rightSquare.leftUp <= sum &&
      leftSquare.rightDown + rightSquare.leftDown <= sum)
      Option(new PairOfSquares(leftSquare, rightSquare))
    else
      Option.empty
  }
}