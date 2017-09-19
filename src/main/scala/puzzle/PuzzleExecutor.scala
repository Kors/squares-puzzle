package puzzle

import puzzle.objects._

object PuzzleExecutor {

  def execute(squares: Seq[Square]): List[FullPuzzle] = {
    val pairs = getValues(squares, squares, PairOfSquares.apply)
    println("pairs size:" + pairs.size)
    val groupsOfFour = getValues(pairs, pairs, SquareOfSquares.apply)
    println("groups of 4 size:" + groupsOfFour.size)
    val groupsOfSix = getValues(groupsOfFour, groupsOfFour, SixOfSquares.apply)
    println("groups of 6 size:" + groupsOfSix.size)
    val groupsOfEight = getValues(groupsOfSix, groupsOfFour, EightOfSquares.apply)
    println("groups of 8 size:" + groupsOfEight.size)
    val groupsOfTen = getValues(groupsOfEight, groupsOfFour, TenOfSquares.apply)
    println("groups of 10 size:" + groupsOfTen.size)
    getValues(groupsOfTen, groupsOfFour, FullPuzzle.apply)
  }

  def getValues[T, R, S](firstList: Seq[T], secondList: Seq[R], function2: Function2[T, R, Option[S]]): List[S] = {
    firstList.par.flatMap(list1 =>
      secondList.filter(list => list != list1).map(list2 => function2(list1, list2))
    ).filter(o => o.isDefined).map(o => o.get).toList
  }

}
