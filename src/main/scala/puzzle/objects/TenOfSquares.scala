package puzzle.objects

case class TenOfSquares(_1: Square, _2: Square,
                        _3: Square, _4: Square, _5: Square,
                        _6: Square, _7: Square, _8: Square,
                        _9: Square, _10: Square) {

  def leftConnectingSide: List[Square] = List(_3, _6)

}

object TenOfSquares {
  private val sum = 10

  def correctSum(_1: Int, _2: Int, _3: Int): Boolean = _1 + _2 + _3 <= sum

  def apply(central: EightOfSquares, rightSoS: SquareOfSquares): Option[TenOfSquares] = {
    if (central.rightConnectingSide == rightSoS.leftSide &&
      hasNoDuplicates(central, rightSoS) &&
      correctSum(central._2.rightDown, central._4.rightUp, rightSoS.rightUpSquare.leftUp) &&
      correctSum(central._8.rightUp, central._6.rightDown, rightSoS.rightDownSquare.leftDown)
    )
      Option(
        new TenOfSquares(
          central._1, central._2,
          central._3, central._4, rightSoS.rightUpSquare,
          central._5, central._6, rightSoS.rightDownSquare,
          central._7, central._8
        )
      )
    else
      Option.empty
  }

  private def hasNoDuplicates(central: EightOfSquares, rightSoS: SquareOfSquares): Boolean = {
    Set(central._1, central._2,
      central._3, rightSoS.rightUpSquare,
      central._5, rightSoS.rightDownSquare,
      central._7, central._8).size == 8
  }
}