package puzzle.objects

import org.scalatest.FunSpec

class PairOfSquaresTest extends FunSpec {

  describe("Factory tests") {

    it("create pair successfully") {
      val sq1 = Square("1 2 3 4")
      val sq2 = Square("4 3 2 1")

      val optionPair = PairOfSquares(sq1, sq2)
      assert(optionPair.isDefined)

      val pair = optionPair.get
      assertResult(sq1)(pair.leftSquare)
      assertResult(sq2)(pair.rightSquare)
      assertResult(Seq(sq1, sq2))(pair.seq)
    }

    it("fail to create pair (incorrect sum)") {
      val sq1 = Square("1 6 1 6")
      val sq2 = Square("1 1 5 1")

      var optionPair1 = PairOfSquares(sq1, sq2)
      assert(optionPair1.isEmpty)

      val sq3 = Square("5 1 1 1")
      val optionPair2 = PairOfSquares(sq1, sq3)
      assert(optionPair2.isEmpty)
    }

  }

}
