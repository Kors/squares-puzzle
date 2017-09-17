package puzzle.objects

case class FullPuzzle(_1: Square, _2: Square,
                      _3: Square, _4: Square, _5: Square, _6: Square,
                      _7: Square, _8: Square, _9: Square, _10: Square,
                      _11: Square, _12: Square) {

  override def toString: String =
    List(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12)
      .map(sq => sq.toFormattedString).toStream.mkString

}

object FullPuzzle {
  private val sum = 10

  def correctSum(_1: Int, _2: Int, _3: Int): Boolean = _1 + _2 + _3 <= sum

  def apply(central: TenOfSquares, leftSoS: SquareOfSquares): Option[FullPuzzle] = {
    if (central.leftConnectingSide == leftSoS.rightSide &&
      hasNoDuplicates(central, leftSoS) &&
      correctSum(central._1.leftDown, central._3.leftUp, leftSoS.leftUpSquare.rightUp) &&
      correctSum(leftSoS.leftDownSquare.rightDown, central._6.leftDown, central._9.leftUp)
    )
      Option(
        new FullPuzzle(
          central._1, central._2,
          leftSoS.leftUpSquare, central._3, central._4, central._5,
          leftSoS.leftDownSquare, central._6, central._7, central._8,
          central._9, central._10
        )
      )
    else
      Option.empty
  }

  private def hasNoDuplicates(central: TenOfSquares, leftSoS: SquareOfSquares): Boolean = {
    Set(central._1, central._2,
      leftSoS.leftUpSquare, central._4, central._5,
      leftSoS.leftDownSquare, central._7, central._8,
      central._9, central._10).size == 10
  }
}