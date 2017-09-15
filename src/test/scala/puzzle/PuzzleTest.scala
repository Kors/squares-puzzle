package puzzle

import org.scalatest.FunSpec

import scala.collection.mutable.ArrayBuffer

class PuzzleTest extends FunSpec {
  info("Starting...")

  val filename = "src/main/resources/example1.txt"

  describe("Read") {
    it("read from file successfully") {
      val squares = DataReader.read(filename)
      assert(squares.nonEmpty)
    }
  }

  describe("Group") {
    it("move squares into groups") {
      val squares = DataReader.read(filename)
      val groups = PuzzleExecutor.getAllMatchedQuarters(squares)
      print(groups)
      assert(groups.nonEmpty)
    }
  }

  def print(vals: ArrayBuffer[List[Square]]): Unit = {
    println()
    for (e <- vals)
      println(e)
  }


}
