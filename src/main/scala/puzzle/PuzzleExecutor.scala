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

  def getValues[T, R, S](firstList: List[T], secondList: List[R], function2: Function2[T, R, Option[S]]): List[S] = {
    val buf: ArrayBuffer[S] = ArrayBuffer()
    firstList.foreach(list1 =>
      secondList.filter(list => list != list1).toStream.foreach(list2 =>
        function2(list1, list2).foreach(set => buf += set)
      )
    )
    buf.toList
  }

  def getAllMatchedPairs(squares: List[Square]): List[PairOfSquares] =
    getValues(squares, squares, PairOfSquares.apply)

  def getAllMatchedQuarters(pairs: List[PairOfSquares]): List[SquareOfSquares] =
    getValues(pairs, pairs, SquareOfSquares.apply)

  def getAllMatchedSixs(sqOfSq: List[SquareOfSquares]): List[SixOfSquares] =
    getValues(sqOfSq, sqOfSq, SixOfSquares.apply)

  def getAllMatchedEights(sixOfSq: List[SixOfSquares], fourOfSq: List[SquareOfSquares]): List[EightOfSquares] =
    getValues(sixOfSq, fourOfSq, EightOfSquares.apply)

  def getAllMatchedTens(eightOfSq: List[EightOfSquares], fourOfSq: List[SquareOfSquares]): List[TenOfSquares] =
    getValues(eightOfSq, fourOfSq, TenOfSquares.apply)

  def getResults(tenOfSq: List[TenOfSquares], fourOfSq: List[SquareOfSquares]): List[FullPuzzle] =
    getValues(tenOfSq, fourOfSq, FullPuzzle.apply)

}
