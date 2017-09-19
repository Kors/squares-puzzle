package puzzle.objects

object FigureUtils {
  def maxSum = 10

  def hasNoDuplicates(seq: Seq[Square]): Boolean = seq.size == seq.distinct.size

  // для каждой фигуры считает что все "новые" стыки объектов соответствуют условиям
  // те условия, которые предполагаются проверенными ранее, не учитываются.
  // (да, для понимания сложно, но проще это написать не выходит т.к. очень разные условия на каждом шаге)

  def isCorrect(seq: Seq[Square]): Boolean = {

    seq.size match {
      case (2) => hasNoDuplicates(seq) &&
        seq.head.rightUp + seq(1).leftUp <= maxSum && seq.head.rightDown + seq(1).leftDown <= maxSum

      case (4) => hasNoDuplicates(seq) &&
        seq.head.rightDown + seq(1).leftDown + seq(2).rightUp + seq(3).leftUp == maxSum &&
        seq.head.leftDown + seq(2).leftUp <= maxSum &&
        seq(1).rightDown + seq(3).rightUp <= maxSum

      case (6) => hasNoDuplicates(seq)
      case (8) => hasNoDuplicates(seq)

      case (10) => hasNoDuplicates(seq) &&
        seq(1).rightDown + seq(3).rightUp + seq(4).leftUp <= maxSum &&
        seq(6).rightDown + seq(7).leftDown + seq(9).rightUp <= maxSum

      case (12) => hasNoDuplicates(seq) &&
        seq.head.leftDown + seq(2).rightUp + seq(3).leftUp <= maxSum &&
        seq(6).rightDown + seq(7).leftDown + seq(10).leftUp <= maxSum

      case _ => false
    }

  }
}
