package puzzle

import org.scalatest.FunSpec

import scala.collection.mutable.ArrayBuffer

class PuzzleTest extends FunSpec {
  info("Starting...")

  val inputFileName = "src/main/resources/example4.txt"
  val outputFileName = "src/main/resources/output.txt"

  describe("Read") {
    it("read from file successfully") {
      val squares = DataIO.read(inputFileName)
      assert(squares.size == 12, squares)
    }
  }

  describe("Group") {
    it("move squares into groups") {
      val squares = DataIO.read(inputFileName)
      val groups = PuzzleExecutor.getAllMatchedQuarters(squares)
      assert(groups.nonEmpty)
      groups.foreach(group => {
        assert(group.leftUpSquare != group.rightUpSquare, group)
        assert(group.leftUpSquare != group.leftDownSquare, group)
        assert(group.leftUpSquare != group.rightDownSquare, group)
        assert(group.rightUpSquare != group.leftDownSquare, group)
        assert(group.rightUpSquare != group.rightDownSquare, group)
        assert(group.leftDownSquare != group.rightDownSquare, group)
      })
      println("Groups found:" + groups.size)
    }

    it("filter groups") {
      val squares = DataIO.read(inputFileName)
      val groups = PuzzleExecutor.getAllMatchedQuarters(squares)
      val filteredGroups = PuzzleExecutor.filterGroups(groups)
      assert(filteredGroups.nonEmpty)
      assert(filteredGroups.size < groups.size)
      println("After filtering:" + filteredGroups.size)
    }

    it("results") {
      val squares = DataIO.read(inputFileName)
      val groups = PuzzleExecutor.getAllMatchedQuarters(squares)
      val filteredGroups = PuzzleExecutor.filterGroups(groups)
      val results = PuzzleExecutor.mergeIntoResult(filteredGroups, groups)
      assert(results.nonEmpty)
      println("Results size:" + results.size)
    }

    it("final results") {
      val squares = DataIO.read(inputFileName)
      val groups = PuzzleExecutor.getAllMatchedQuarters(squares)
      val filteredGroups = PuzzleExecutor.filterGroups(groups)
      val results = PuzzleExecutor.mergeIntoResult(filteredGroups, groups)
      val finalResults = PuzzleExecutor.filterResultsToFinal(results)
      print(finalResults)
      assert(finalResults.nonEmpty)
      println("FinalResults size:" + finalResults.size)
    }

  }

  def print(vals: ArrayBuffer[List[Square]]): Unit = {
    println()
    for (e <- vals)
      println(e)
  }


}
