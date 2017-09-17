package puzzle.objects

case class EightOfSquares(_1: Square, _2: Square,
                          _3: Square, _4: Square,
                          _5: Square, _6: Square,
                          _7: Square, _8: Square) {

  def leftConnectingSide: List[Square] = List(_3, _5)

  def rightConnectingSide: List[Square] = List(_4, _6)

}

object EightOfSquares {
  def apply(upperSoS: SixOfSquares, downSoS: SixOfSquares): Option[EightOfSquares] = {
    if (upperSoS.downFour == downSoS.upperFour &&
      hasNoDuplicates(upperSoS, downSoS))
      Option(
        new EightOfSquares(
          upperSoS.leftUpSquare, upperSoS.rightUpSquare,
          upperSoS.leftCentralSquare, upperSoS.rightCentralSquare,
          upperSoS.leftDownSquare, upperSoS.rightDownSquare,
          downSoS.leftDownSquare, downSoS.rightDownSquare
        )
      )
    else
      Option.empty
  }

  private def hasNoDuplicates(upperSoS: SixOfSquares, downSoS: SixOfSquares): Boolean =
    upperSoS.upSide.intersect(downSoS.downSide).isEmpty
}
