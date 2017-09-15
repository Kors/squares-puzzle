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
              if (s1.leftDown + s3.leftUp < 10 &&
                s1.rightUp + s2.leftUp < 10 &&
                s2.rightDown + s4.rightUp < 10 &&
                s3.rightDown + s4.leftDown < 10)
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

  def mergeIntoResult(centralGroups: ArrayBuffer[List[Square]], groups: ArrayBuffer[List[Square]]): ArrayBuffer[List[Square]] = {
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
                      groupLists += List[Square](o1.head, o1(1), o3.head, g.head, g(1), o4(1), o3(2), g(2), g(3), o4(3), o2(2), o2(3))
                    }
                }
            }
        }
    })
    groupLists
  }

}
