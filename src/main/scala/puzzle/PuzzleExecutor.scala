package puzzle

import puzzle.objects._

import scala.collection.mutable.ArrayBuffer

object PuzzleExecutor {

  def execute(squares: List[Square]): List[FullPuzzle] = {
    val pairs = getValues(squares, squares, PairOfSquares.apply)
    val groupsOfFour = getValues(pairs, pairs, SquareOfSquares.apply)
    val groupsOfSix = getValues(groupsOfFour, groupsOfFour, SixOfSquares.apply)
    val groupsOfEight = getValues(groupsOfSix, groupsOfFour, EightOfSquares.apply)
    val groupsOfTen = getValues(groupsOfTen, groupsOfFour, TenOfSquares.apply)
    getValues(groupsOfTen, groupsOfFour, FullPuzzle.apply)
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

}
