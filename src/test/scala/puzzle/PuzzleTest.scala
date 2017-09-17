package puzzle

import org.scalatest.FunSpec

import scala.io.Source

class PuzzleTest extends FunSpec {
  info("Starting...")

  val inputFileName = "src/main/resources/example4.txt"
  val outputFileName = "src/main/resources/output.txt"
  val resultToCompare = "src/main/resources/output4.txt"

  describe("Read") {
    it("read from file successfully") {
      val squares = DataIO.read(inputFileName)
      assert(squares.size == 12, squares)
    }
  }

  describe("Group") {
    it("final results") {
      val squares = DataIO.read(inputFileName)
      println("squares size:" + squares.size)
      val pairs = PuzzleExecutor.getAllMatchedPairs(squares)
      println("pairs size:" + pairs.size)
      val groups = PuzzleExecutor.getAllMatchedQuarters(pairs)
      println("groups size:" + groups.size)
      val filteredGroups = PuzzleExecutor.filterGroups(groups)
      println("filteredGroups size:" + filteredGroups.size)
      val results = PuzzleExecutor.mergeIntoResult(filteredGroups, groups)
      println("results size:" + results.size)
      val finalResults = PuzzleExecutor.filterResultsToFinal(results)
      print(finalResults)
      assert(finalResults.nonEmpty)
      println("FinalResults size:" + finalResults.size)
      val output = read(outputFileName)
      val correct = read(resultToCompare)
      assert(output == correct)
    }
  }

  def read(fileName: String): List[String] =
    Source.fromFile(fileName).getLines.toList


  def print(vals: List[List[Square]]): Unit = {
    println()
    for (e <- vals)
      println(e)
  }


}
