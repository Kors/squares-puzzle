package puzzle.objects

class PairOfSquares(val seq: Seq[Square]) {

  def leftSquare: Square = seq.head

  def rightSquare: Square = seq(1)

}

object PairOfSquares extends CompoundFigure {

  def apply(leftSquare: Square, rightSquare: Square): Option[PairOfSquares] = {

    val seq = Seq(leftSquare, rightSquare)

    if (isCorrect(seq))
      Option(new PairOfSquares(seq))
    else
      None
  }
}