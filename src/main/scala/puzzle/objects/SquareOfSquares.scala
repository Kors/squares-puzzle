package puzzle.objects

class SquareOfSquares(val seq: Seq[Square]) extends Figure {

  val leftUpSquare: Square = seq.head
  val rightUpSquare: Square = seq(1)
  val leftDownSquare: Square = seq(2)
  val rightDownSquare: Square = seq(3)

  def upSide: List[Square] = List(leftUpSquare, rightUpSquare)

  def downSide: List[Square] = List(leftDownSquare, rightDownSquare)

  def leftSide: List[Square] = List(leftUpSquare, leftDownSquare)

  def rightSide: List[Square] = List(rightUpSquare, rightDownSquare)

}

object SquareOfSquares {

  def apply(upPair: PairOfSquares, downPair: PairOfSquares): Option[SquareOfSquares] = {

    def seq = Seq(upPair.leftSquare, upPair.rightSquare, downPair.leftSquare, downPair.rightSquare)

    if (FigureUtils.isCorrect(seq))
      Option(new SquareOfSquares(seq))
    else
      None

  }
}
