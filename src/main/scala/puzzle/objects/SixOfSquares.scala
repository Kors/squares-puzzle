package puzzle.objects

class SixOfSquares(val seq: Seq[Square]) {

  private val leftDownSquare: Square = seq(4)
  private val rightDownSquare: Square = seq(5)

  def downSide: Seq[Square] = Seq(leftDownSquare, rightDownSquare)

}

object SixOfSquares {

  def apply(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Option[SixOfSquares] = {

    def seq = upperSoS.upSide ++ centralSoS.seq

    def sideMatches(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Boolean =
      centralSoS.upSide == upperSoS.downSide

    if (sideMatches(centralSoS, upperSoS) && FigureUtils.isCorrect(seq))
      Option(new SixOfSquares(seq))
    else
      None
  }

}
