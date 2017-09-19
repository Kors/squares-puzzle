package puzzle.objects

class SquareOfSquares(val seq: Seq[Square]) {

  private val leftUpSquare: Square = seq.head
  private val rightUpSquare: Square = seq(1)
  private val leftDownSquare: Square = seq(2)
  private val rightDownSquare: Square = seq(3)

  def upSide: Seq[Square] = Seq(leftUpSquare, rightUpSquare)

  def downSide: Seq[Square] = Seq(leftDownSquare, rightDownSquare)

  def leftSide: Seq[Square] = Seq(leftUpSquare, leftDownSquare)

  def rightSide: Seq[Square] = Seq(rightUpSquare, rightDownSquare)

}

object SquareOfSquares extends CompoundFigure {

  def apply(upPair: PairOfSquares, downPair: PairOfSquares): Option[SquareOfSquares] = {

    def seq = upPair.seq ++ downPair.seq

    if (isCorrect(seq))
      Option(new SquareOfSquares(seq))
    else
      None

  }
}
