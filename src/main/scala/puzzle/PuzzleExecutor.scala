package puzzle

import scala.collection.mutable.ArrayBuffer

object PuzzleExecutor {

  // будет выбирать те четвёрки, которые в по сумме углов дают десятку
  // пока совсем не функционально
  def getAllMatchedQuarters(squares: List[Square]): ArrayBuffer[SquareOfSquares] = {
    val buf: ArrayBuffer[SquareOfSquares] = ArrayBuffer()
    for (s1 <- squares) {
      val sub1 = squares.filter(s => s != s1) // TODO проверить корректность такой фильтрации на двух разных элементах с одинаковым содержимым
      for (s2 <- sub1) {
        val sub2 = sub1.filter(s => s != s2)
        for (s3 <- sub2.tail) {
          val sub3 = sub2.filter(s => s != s3)
          for (s4 <- sub3) {
            for (set <- SquareOfSquares.apply(s1, s2, s3, s4)) {
              buf += set
            }
          }
        }
      }
    }
    buf
  }

  // TODO refactor
  def filterGroups(groups: ArrayBuffer[SquareOfSquares]): ArrayBuffer[SquareOfSquares] = {
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

  def mergeIntoResult(centralGroups: ArrayBuffer[SquareOfSquares], groups: ArrayBuffer[SquareOfSquares]): ArrayBuffer[List[Square]] = {
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
    groupLists
  }

  def eachElementOnece(list: List[Square]): Boolean = {
    val set = Set(list: _*)
    if (set.size == 12)
      true
    else
      false
  }

  // TODO фильтр результатов по тройкам
  def filterResultsToFinal(results: ArrayBuffer[List[Square]]): ArrayBuffer[List[Square]] = {
    val finalResults = results.filter(list => {
      if (list(0).leftDown + list(2).rightUp + list(3).leftUp <= 10 &&
        list(1).rightDown + list(4).rightUp + list(5).leftUp <= 10 &&
        list(6).rightDown + list(7).leftDown + list(10).leftUp <= 10 &&
        list(8).rightDown + list(9).leftDown + list(11).rightUp <= 10)
        true
      else
        false
    })
    finalResults
  }

}
