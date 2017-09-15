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
