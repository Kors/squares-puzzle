package puzzle

import org.scalatest.FunSpec

import scala.collection.mutable.ArrayBuffer

class PuzzleTest extends FunSpec {
  info("Starting...")

  val filename = "src/main/resources/example4.txt"

  describe("Read") {
    it("read from file successfully") {
      val squares = DataReader.read(filename)
      assert(squares.size == 12, squares)
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
      println("Groups found:" + groups.size)
    }

    it("filter groups") {
      val squares = DataReader.read(filename)
      val groups = PuzzleExecutor.getAllMatchedQuarters(squares)
      val filteredGroups = PuzzleExecutor.filterGroups(groups)
      print(filteredGroups)
      assert(filteredGroups.nonEmpty)
      assert(filteredGroups.size < groups.size)
      println("After filtering:" + filteredGroups.size)
    }

    it("results") {
      val squares = DataReader.read(filename)
      val groups = PuzzleExecutor.getAllMatchedQuarters(squares)
      val filteredGroups = PuzzleExecutor.filterGroups(groups)
      val results = PuzzleExecutor.mergeIntoResult(filteredGroups, groups)
      print(results)
      assert(results.nonEmpty)
      println("Results size:" + results.size)
    }
  }

  def print(vals: ArrayBuffer[List[Square]]): Unit = {
    println()
    for (e <- vals)
      println(e)
  }


}
