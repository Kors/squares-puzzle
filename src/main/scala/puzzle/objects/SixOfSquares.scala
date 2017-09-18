package puzzle.objects

class SixOfSquares(val seq: Seq[Square]) {

  val leftDownSquare: Square = seq(4)
  val rightDownSquare: Square = seq(5)

  def downSide: Seq[Square] = Seq(leftDownSquare, rightDownSquare)

}

object SixOfSquares {

  def apply(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Option[SixOfSquares] = {

    def seq: Seq[Square] = (centralSoS.seq ++ upperSoS.seq).distinct

    def sideMatches(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Boolean =
      centralSoS.upSide == upperSoS.downSide

    if (sideMatches(centralSoS, upperSoS) && seq.size == 6 && FigureUtils.isCorrect(seq))
      Option(new SixOfSquares(seq))
    else
      None
  }

}
