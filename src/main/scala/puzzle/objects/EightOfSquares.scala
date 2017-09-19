package puzzle.objects

class EightOfSquares(val seq: Seq[Square]) {

  def leftConnectingSide: Seq[Square] = Seq(seq(2), seq(4))

  def rightConnectingSide: Seq[Square] = Seq(seq(3), seq(5))

}

object EightOfSquares {

  def apply(central: SixOfSquares, downSoS: SquareOfSquares): Option[EightOfSquares] = {

    val base = central.seq

    def seq = Seq(base.head, base(1), base(2), base(3), base(4), base(5), downSoS.leftDownSquare, downSoS.rightDownSquare)

    def sideMatches(central: SixOfSquares, downSoS: SquareOfSquares): Boolean =
      central.downSide == downSoS.upSide

    if (sideMatches(central, downSoS) && FigureUtils.isCorrect(seq))
      Option(new EightOfSquares(seq))
    else
      None

  }

}
