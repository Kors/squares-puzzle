package puzzle.objects

class SixOfSquares(val leftUpSquare: Square, val rightUpSquare: Square,
                   val leftCentralSquare: Square, val rightCentralSquare: Square,
                   val leftDownSquare: Square, val rightDownSquare: Square) {
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
      None
  }

  private def sideMatches(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Boolean = {
    centralSoS.upSide == upperSoS.downSide
  }

  private def hasNoDuplicates(centralSoS: SquareOfSquares, upperSoS: SquareOfSquares): Boolean = {
    Set(centralSoS.leftDownSquare, centralSoS.rightDownSquare, upperSoS.leftUpSquare, upperSoS.rightUpSquare).size == 4
  }
}
