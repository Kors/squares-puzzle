package puzzle.objects

class SquareOfSquares(val seq: Seq[Square]) extends Figure {

  val leftUpSquare: Square = seq.head
  val rightUpSquare: Square = seq(1)
  val leftDownSquare: Square = seq(2)
  val rightDownSquare: Square = seq(3)

  def upSide: Seq[Square] = Seq(leftUpSquare, rightUpSquare)

  def downSide: Seq[Square] = Seq(leftDownSquare, rightDownSquare)

  def leftSide: Seq[Square] = Seq(leftUpSquare, leftDownSquare)

  def rightSide: Seq[Square] = Seq(rightUpSquare, rightDownSquare)

}

object SquareOfSquares {

  def apply(upPair: PairOfSquares, downPair: PairOfSquares): Option[SquareOfSquares] = {

    def seq = upPair.seq ++ downPair.seq

    if (FigureUtils.isCorrect(seq))
      Option(new SquareOfSquares(seq))
    else
      None

  }
}
