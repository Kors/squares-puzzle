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
      groups.foreach(group => {
        assert(group.size == 4)
        assert(group.head != group(1), group)
        assert(group.head != group(2), group)
        assert(group.head != group(3), group)
        assert(group(1) != group(2), group)
        assert(group(1) != group(3), group)
        assert(group(2) != group(3), group)
      })
    }
  }

  def print(vals: ArrayBuffer[List[Square]]): Unit = {
    println()
    for (e <- vals)
      println(e)
  }


}
