package puzzle

import org.scalatest.FunSpec

class PuzzleTest extends FunSpec {
  info("Starting...")

  val filename = "src/main/resources/example1.txt"

  describe("Read") {
    it("read from file successfully") {
      val reader = new DataReader
      val squares = reader.read(filename)
      for (s <- squares)
        println(s)
      assert(squares.nonEmpty)
    }
  }

}
