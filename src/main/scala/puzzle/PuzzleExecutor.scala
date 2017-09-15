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
      for (other <- groups.filter(o => o != g)) {
        if (g.head == other(2) && g(1) == other(3))
          if (!other.contains(g(2)) &&
            !other.contains(g(3)))
            flag = true
      }
      flag
    })
    val downEquality = upEquality.filter(g => {
      var flag = false
      for (other <- groups.filter(o => o != g)) {
        if (g(2) == other.head && g(3) == other(1))
          if (!other.contains(g.head) &&
            !other.contains(g(1)))
            flag = true
      }
      flag
    })
    val leftEquality = downEquality.filter(g => {
      var flag = false
      for (other <- groups.filter(o => o != g)) {
        if (g.head == other(1) && g(2) == other(3))
          if (!other.contains(g(1)) &&
            !other.contains(g(3)))
            flag = true
      }
      flag
    })
    val rightEquality = leftEquality.filter(g => {
      var flag = false
      for (other <- groups.filter(o => o != g)) {
        if (g(1) == other.head && g(3) == other(2))
          if (!other.contains(g.head) &&
            !other.contains(g(2)))
            flag = true
      }
      flag
    })
    rightEquality
  }

}
