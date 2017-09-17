package puzzle

import puzzle.objects._

import scala.collection.mutable.ArrayBuffer

object PuzzleExecutor {

  def execute(squares: List[Square]): List[FullPuzzle] = {
    val pairs = getAllMatchedPairs(squares)
    val groupsOfFour = PuzzleExecutor.getAllMatchedQuarters(pairs)
    val groupsOfSix = getAllMatchedSixs(groupsOfFour)
    val groupsOfEight = getAllMatchedEights(groupsOfSix, groupsOfFour)
    val groupsOfTen = getAllMatchedTens(groupsOfEight, groupsOfFour)
    PuzzleExecutor.getResults(groupsOfTen, groupsOfFour)
  }

  def getAllMatchedPairs(squares: List[Square]): List[PairOfSquares] = {
    val buf: ArrayBuffer[PairOfSquares] = ArrayBuffer()
    squares.foreach(s1 =>
      squares.filter(s => s != s1).toStream.foreach(s2 =>
        PairOfSquares.apply(s1, s2).foreach(set => buf += set)
      )
    )
    buf.toList
  }

  def getAllMatchedQuarters(pairs: List[PairOfSquares]): List[SquareOfSquares] = {
    val buf: ArrayBuffer[SquareOfSquares] = ArrayBuffer()
    pairs.foreach(p1 =>
      pairs.filter(p => p != p1).foreach(p2 =>
        SquareOfSquares.apply(p1, p2).foreach(set => buf += set)
      )
    )
    buf.toList
  }

  def getAllMatchedSixs(sqOfSq: List[SquareOfSquares]): List[SixOfSquares] = {
    val buf: ArrayBuffer[SixOfSquares] = ArrayBuffer()
    sqOfSq.foreach(sqSet1 =>
      sqOfSq.filter(sqSet2 => sqSet2 != sqSet1).foreach(sqSet2 =>
        SixOfSquares.apply(sqSet1, sqSet2).foreach(set => buf += set)
      )
    )
    buf.toList
  }

  def getAllMatchedEights(sixOfSq: List[SixOfSquares], fourOfSq: List[SquareOfSquares]): List[EightOfSquares] = {
    val buf: ArrayBuffer[EightOfSquares] = ArrayBuffer()
    sixOfSq.foreach(sqSet1 =>
      fourOfSq.foreach(sqSet2 =>
        EightOfSquares.apply(sqSet1, sqSet2).foreach(set => buf += set)
      )
    )
    buf.toList
  }

  def getAllMatchedTens(eightOfSq: List[EightOfSquares], fourOfSq: List[SquareOfSquares]): List[TenOfSquares] = {
    val buf: ArrayBuffer[TenOfSquares] = ArrayBuffer()
    eightOfSq.foreach(sqSet1 =>
      fourOfSq.foreach(sqSet2 =>
        TenOfSquares.apply(sqSet1, sqSet2).foreach(set => buf += set)
      )
    )
    buf.toList
  }

  def getResults(tenOfSq: List[TenOfSquares], fourOfSq: List[SquareOfSquares]): List[FullPuzzle] = {
    val buf: ArrayBuffer[FullPuzzle] = ArrayBuffer()
    tenOfSq.foreach(sqSet1 =>
      fourOfSq.foreach(sqSet2 =>
        FullPuzzle.apply(sqSet1, sqSet2).foreach(set => buf += set)
      )
    )
    buf.toList
  }

}
