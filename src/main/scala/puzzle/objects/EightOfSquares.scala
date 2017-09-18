package puzzle.objects

class EightOfSquares(val _1: Square, val _2: Square,
                     val _3: Square, val _4: Square,
                     val _5: Square, val _6: Square,
                     val _7: Square, val _8: Square) {

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
