package puzzle.objects

case class EightOfSquares(_1: Square, _2: Square,
                          _3: Square, _4: Square,
                          _5: Square, _6: Square,
                          _7: Square, _8: Square) {

  def leftConnectingSide: List[Square] = List(_3, _5)

  def rightConnectingSide: List[Square] = List(_4, _6)

}

object EightOfSquares {
  def apply(central: SixOfSquares, downSoS: SquareOfSquares): Option[EightOfSquares] = {
    if (central.downSide == downSoS.upSide &&
      hasNoDuplicates(central, downSoS))
      Option(
        new EightOfSquares(
          central.leftUpSquare, central.rightUpSquare,
          central.leftCentralSquare, central.rightCentralSquare,
          central.leftDownSquare, central.rightDownSquare,
          downSoS.leftDownSquare, downSoS.rightDownSquare
        )
      )
    else
      Option.empty
  }

  private def hasNoDuplicates(central: SixOfSquares, downSoS: SquareOfSquares): Boolean = {
    Set(central.leftUpSquare, central.rightUpSquare,
      central.leftCentralSquare, central.rightCentralSquare,
      downSoS.leftDownSquare, downSoS.rightDownSquare).size == 6
  }
}
