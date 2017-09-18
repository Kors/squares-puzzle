package puzzle.objects

object FigureUtils {
  def maxSum = 10

  def hasNoDuplicates(seq: Seq[Square]): Boolean = seq.size == seq.distinct.size


  def isCorrect(seq: Seq[Square]): Boolean = {
    seq.size match {
      case (2) => seq.head.rightUp + seq(1).leftUp <= maxSum && seq.head.rightDown + seq(1).leftDown <= maxSum
    }
  }
}
