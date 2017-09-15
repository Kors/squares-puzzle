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

}
