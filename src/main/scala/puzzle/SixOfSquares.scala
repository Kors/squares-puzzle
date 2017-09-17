package puzzle

case class SixOfSquares(leftUpSquare: Square, rightUpSquare: Square,
                        leftCentralSquare: Square, rightCentralSquare: Square,
                        leftDownSquare: Square, rightDownSquare: Square) {
  def downSide: List[Square] = {
    List(leftDownSquare, rightDownSquare)
  }
}

object SixOfSquares {
  def apply(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Option[SixOfSquares] = {
    if (sideMatches(centralSoS, upperSoS) &&
      hasNoDuplicates(centralSoS, upperSoS))
      Option(
        new SixOfSquares(
          upperSoS.leftUpSquare, upperSoS.rightUpSquare,
          centralSoS.leftUpSquare, centralSoS.rightUpSquare,
          centralSoS.leftDownSquare, centralSoS.rightDownSquare
        )
      )
    else
      Option.empty
  }

  private def sideMatches(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Boolean = {
    if (centralSoS.leftUpSquare == upperSoS.leftDownSquare &&
      centralSoS.rightUpSquare == upperSoS.rightDownSquare)
      true
    else
      false
  }

  private def hasNoDuplicates(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Boolean = {
    Set(centralSoS.leftDownSquare, centralSoS.rightDownSquare, upperSoS.leftUpSquare, upperSoS.rightUpSquare).size == 4
  }
}
