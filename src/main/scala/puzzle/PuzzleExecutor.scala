package puzzle

import scala.collection.mutable.ArrayBuffer

object PuzzleExecutor {

  // будет выбирать те четвёрки, которые в по сумме углов дают десятку
  // пока совсем не функционально
  def getAllMatchedQuarters(squares: List[Square]): ArrayBuffer[List[Square]] = {
    val buf: ArrayBuffer[List[Square]] = ArrayBuffer()
    for (s1 <- squares) {
      val sub1 = squares.filter(s => s != s1) // TODO проверить корректность такой фильтрации на двух разных элементах с одинаковым содержимым
      for (s2 <- sub1) {
        val sub2 = sub1.filter(s => s != s2)
        for (s3 <- sub2.tail) {
          val sub3 = sub2.filter(s => s != s3)
          for (s4 <- sub3) {
            if (s1.rightDown + s2.leftDown + s3.rightUp + s4.leftUp == 10)
              buf += List(s1, s2, s3, s4)
          }
        }
      }
    }
    buf
  }

  // TODO refactor
  def filterGroups(groups: ArrayBuffer[List[Square]]): ArrayBuffer[List[Square]] = {
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

  def matchesUpperSide(centralGroup: List[Square], anotherGroup: List[Square]): Boolean = {
    if (centralGroup.head == anotherGroup(2) &&
      centralGroup(1) == anotherGroup(3) &&
      !anotherGroup.contains(centralGroup(2)) &&
      !anotherGroup.contains(centralGroup(3)))
      true
    else
      false
  }

  def matchesDownSide(centralGroup: List[Square], anotherGroup: List[Square]): Boolean = {
    if (centralGroup(2) == anotherGroup.head &&
      centralGroup(3) == anotherGroup(1) &&
      !anotherGroup.contains(centralGroup.head) &&
      !anotherGroup.contains(centralGroup(1)))
      true
    else
      false
  }

  def matchesLeftSide(centralGroup: List[Square], anotherGroup: List[Square]): Boolean = {
    if (centralGroup.head == anotherGroup(1) &&
      centralGroup(2) == anotherGroup(3) &&
      !anotherGroup.contains(centralGroup(1)) &&
      !anotherGroup.contains(centralGroup(3)))
      true
    else
      false
  }

  def matchesRightSide(centralGroup: List[Square], anotherGroup: List[Square]): Boolean = {
    if (centralGroup(1) == anotherGroup.head &&
      centralGroup(3) == anotherGroup(2) &&
      !anotherGroup.contains(centralGroup.head) &&
      !anotherGroup.contains(centralGroup(2)))
      true
    else
      false
  }

}
