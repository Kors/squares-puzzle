package puzzle

import scala.collection.mutable.ArrayBuffer

object PuzzleExecutor {

  def execute(squares: List[Square]): List[List[Square]] = {
    val pairs = getAllMatchedPairs(squares)
    val groupsOfFour = PuzzleExecutor.getAllMatchedQuarters(pairs)
    val groupsOfSix = getAllMatchedSixs(groupsOfFour)
    val groupsOfEight = getAllMatchedEights(groupsOfSix, groupsOfFour)
    val filteredGroups = PuzzleExecutor.filterGroups(groupsOfFour)
    val results = PuzzleExecutor.mergeIntoResult(filteredGroups, groupsOfFour)
    PuzzleExecutor.filterResultsToFinal(results)
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

  // TODO refactor
  def filterGroups(groups: List[SquareOfSquares]): List[SquareOfSquares] = {
    val upEquality = groups.filter(g => {
      var flag = false
      for (other <- groups.filter(o => o != g))
        if (matchesUpperSide(g, other))
          flag = true
      flag
    })
    val downEquality = upEquality.filter(g => {
      var flag = false
      for (other <- groups.filter(o => o != g))
        if (matchesDownSide(g, other))
          flag = true
      flag
    })
    val leftEquality = downEquality.filter(g => {
      var flag = false
      for (other <- groups.filter(o => o != g))
        if (matchesLeftSide(g, other))
          flag = true
      flag
    })
    val rightEquality = leftEquality.filter(g => {
      var flag = false
      for (other <- groups.filter(o => o != g))
        if (matchesRightSide(g, other))
          flag = true
      flag
    })
    rightEquality
  }

  def matchesUpperSide(centralGroup: SquareOfSquares, anotherGroup: SquareOfSquares): Boolean = {
    if (centralGroup.upSide == anotherGroup.downSide &&
      centralGroup.downSide.intersect(anotherGroup.upSide).isEmpty)
      true
    else
      false
  }

  def matchesDownSide(centralGroup: SquareOfSquares, anotherGroup: SquareOfSquares): Boolean = {
    if (centralGroup.downSide == anotherGroup.upSide &&
      centralGroup.upSide.intersect(anotherGroup.downSide).isEmpty)
      true
    else
      false
  }

  def matchesLeftSide(centralGroup: SquareOfSquares, anotherGroup: SquareOfSquares): Boolean = {
    if (centralGroup.leftSide == anotherGroup.rightSide &&
      centralGroup.rightSide.intersect(anotherGroup.leftSide).isEmpty)
      true
    else
      false
  }

  def matchesRightSide(centralGroup: SquareOfSquares, anotherGroup: SquareOfSquares): Boolean = {
    if (centralGroup.rightSide == anotherGroup.leftSide &&
      centralGroup.leftSide.intersect(anotherGroup.rightSide).isEmpty)
      true
    else
      false
  }

  def mergeIntoResult(centralGroups: List[SquareOfSquares], groups: List[SquareOfSquares]): List[List[Square]] = {
    val groupLists = new ArrayBuffer[List[Square]]
    centralGroups.foreach(g => {
      val other1 = groups.filter(o1 => o1 != g)
      for (o1 <- other1)
        if (matchesUpperSide(g, o1)) {
          val other2 = other1.filter(o2 => o2 != g)
            .filter(o2 => o2 != o1)
          for (o2 <- other2)
            if (matchesDownSide(g, o2)) {
              val other3 = other2.filter(o3 => o3 != g)
                .filter(o3 => o3 != o1)
                .filter(o3 => o3 != o2)
              for (o3 <- other3)
                if (matchesLeftSide(g, o3)) {
                  val other4 = other3.filter(o4 => o4 != g)
                    .filter(o4 => o4 != o1)
                    .filter(o4 => o4 != o2)
                    .filter(o4 => o4 != o3)
                  for (o4 <- other4)
                    if (matchesRightSide(g, o4)) {
                      val list = List[Square](o1.leftUpSquare, o1.rightUpSquare,
                        o3.leftUpSquare, g.leftUpSquare, g.rightUpSquare, o4.rightUpSquare,
                        o3.leftDownSquare, g.leftDownSquare, g.rightDownSquare, o4.rightDownSquare,
                        o2.leftDownSquare, o2.rightDownSquare)
                      if (eachElementOnece(list))
                        groupLists += list
                    }
                }
            }
        }
    })
    groupLists.toList
  }

  def eachElementOnece(list: List[Square]): Boolean = {
    list.distinct.size == 12
  }

  // TODO фильтр результатов по тройкам
  def filterResultsToFinal(results: List[List[Square]]): List[List[Square]] = {
    results.filter(list => {
      if (list(0).leftDown + list(2).rightUp + list(3).leftUp <= 10 &&
        list(1).rightDown + list(4).rightUp + list(5).leftUp <= 10 &&
        list(6).rightDown + list(7).leftDown + list(10).leftUp <= 10 &&
        list(8).rightDown + list(9).leftDown + list(11).rightUp <= 10)
        true
      else
        false
    })
  }

}
