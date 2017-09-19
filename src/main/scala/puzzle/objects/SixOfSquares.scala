package puzzle.objects

class SixOfSquares(val seq: Seq[Square]) {

  val leftDownSquare: Square = seq(4)
  val rightDownSquare: Square = seq(5)

  def downSide: Seq[Square] = Seq(leftDownSquare, rightDownSquare)

}

object SixOfSquares {

  def apply(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Option[SixOfSquares] = {

    val base = centralSoS.seq

    def seq = Seq(upperSoS.leftUpSquare, upperSoS.rightUpSquare, base.head, base(1), base(2), base(3))

    def sideMatches(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Boolean =
      centralSoS.upSide == upperSoS.downSide

    if (sideMatches(centralSoS, upperSoS) && FigureUtils.isCorrect(seq))
      Option(new SixOfSquares(seq))
    else
      None
  }

}
