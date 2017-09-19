package puzzle.objects

class TenOfSquares(val seq: Seq[Square]) {

  def leftConnectingSide: Seq[Square] = Seq(seq(2), seq(5))

}

object TenOfSquares extends CompoundFigure {

  override protected val figureSize = 10

  def apply(eos: EightOfSquares, rightSoS: SquareOfSquares): Option[TenOfSquares] = {

    val base = eos.seq

    def seq = Seq(
      base.head, base(1),
      base(2), base(3), rightSoS.seq(1),
      base(4), base(5), rightSoS.seq(3),
      base(6), base(7))

    def sideMatches(central: EightOfSquares, rightSoS: SquareOfSquares): Boolean =
      central.rightConnectingSide == rightSoS.leftSide

    if (sideMatches(eos, rightSoS) && isCorrect(seq))
      Option(new TenOfSquares(seq))
    else
      None

  }

}
