package puzzle.objects

import org.scalatest.FunSpec

class SquareTest extends FunSpec {

  describe("Factory tests") {

    it("create square successfully") {
      val square = Square("1 2 3 4")
      assertResult(1)(square.leftUp)
      assertResult(2)(square.rightUp)
      assertResult(3)(square.leftDown)
      assertResult(4)(square.rightDown)
    }

    it("fail to create square (wrong argument numbers)") {
      assertThrows[IllegalArgumentException](Square("12 3 4"))
    }

    it("fail to create square (wrong int value)") {
      assertThrows[IllegalArgumentException](Square("1 2.34 3 4"))
    }

  }

}
