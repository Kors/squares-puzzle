package puzzle.objects

class PairOfSquares(val seq: Seq[Square]) extends Figure {

  def leftSquare: Square = seq.head

  def rightSquare: Square = seq(1)

}

object PairOfSquares {

  def apply(leftSquare: Square, rightSquare: Square): Option[PairOfSquares] = {

    val seq = Seq(leftSquare, rightSquare)

    if (FigureUtils.isCorrect(seq))
      Option(new PairOfSquares(seq))
    else
      None
  }
}