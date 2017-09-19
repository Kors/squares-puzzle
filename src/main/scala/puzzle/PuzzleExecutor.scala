package puzzle

import puzzle.objects._

object PuzzleExecutor {

  def execute(squares: Seq[Square]): List[FullPuzzle] = {
    val pairs = getValues(squares, squares, PairOfSquares.apply)
    val groupsOfFour = getValues(pairs, pairs, SquareOfSquares.apply)
    val groupsOfSix = getValues(groupsOfFour, groupsOfFour, SixOfSquares.apply)
    val groupsOfEight = getValues(groupsOfSix, groupsOfFour, EightOfSquares.apply)
    val groupsOfTen = getValues(groupsOfEight, groupsOfFour, TenOfSquares.apply)
    getValues(groupsOfTen, groupsOfFour, FullPuzzle.apply)
  }

  def getValues[T, R, S](firstSeq: Seq[T], secondSeq: Seq[R], factory: (T, R) => Option[S]): List[S] =
    firstSeq.par
      .flatMap(seq1 => secondSeq.filter(seq => seq != seq1).map(seq => factory(seq1, seq)))
      .filter(o => o.isDefined).map(o => o.get).toList

}
