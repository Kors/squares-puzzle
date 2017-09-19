package puzzle.objects

class FullPuzzle(val seq: Seq[Square]) {

  override def toString: String = seq.map(sq => sq.toFormattedString).toStream.mkString

}

object FullPuzzle extends CompoundFigure {

  def apply(tos: TenOfSquares, leftSoS: SquareOfSquares): Option[FullPuzzle] = {

    val base = tos.seq

    def seq = Seq(
      base.head, base(1),
      leftSoS.seq.head, base(2), base(3), base(4),
      leftSoS.seq(2), base(5), base(6), base(7),
      base(8), base(9))

    def sideMatches(central: TenOfSquares, leftSoS: SquareOfSquares): Boolean =
      central.leftConnectingSide == leftSoS.rightSide

    if (sideMatches(tos, leftSoS) && isCorrect(seq))
      Option(new FullPuzzle(seq))
    else
      None

  }

}
