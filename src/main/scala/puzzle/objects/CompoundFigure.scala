package puzzle.objects

trait CompoundFigure {

  protected val figureSize: Int

  private val maxSum = 10

  private def hasNoDuplicates(seq: Seq[Square]): Boolean = seq.size == seq.distinct.size

  // для каждой фигуры считает что все "новые" стыки объектов соответствуют условиям
  // те условия, которые предполагаются проверенными ранее, не учитываются.
  // (да, для понимания сложно, но проще это написать не выходит т.к. очень разные условия на каждом шаге)

  protected def isCorrect(seq: Seq[Square]): Boolean = {

    seq.size == figureSize &&
      hasNoDuplicates(seq) && {
      seq.size match {
        case (2) =>
          seq.head.rightUp + seq(1).leftUp <= maxSum && seq.head.rightDown + seq(1).leftDown <= maxSum

        case (4) =>
          seq.head.rightDown + seq(1).leftDown + seq(2).rightUp + seq(3).leftUp == maxSum &&
            seq.head.leftDown + seq(2).leftUp <= maxSum &&
            seq(1).rightDown + seq(3).rightUp <= maxSum

        case (6) => true
        case (8) => true

        case (10) =>
          seq(1).rightDown + seq(3).rightUp + seq(4).leftUp <= maxSum &&
            seq(6).rightDown + seq(7).leftDown + seq(9).rightUp <= maxSum

        case (12) =>
          seq.head.leftDown + seq(2).rightUp + seq(3).leftUp <= maxSum &&
            seq(6).rightDown + seq(7).leftDown + seq(10).leftUp <= maxSum

        case _ => false
      }
    }

  }

}

