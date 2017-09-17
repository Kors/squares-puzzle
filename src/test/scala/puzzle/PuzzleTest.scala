package puzzle

import org.scalatest.FunSpec
import puzzle.PuzzleExecutor.{getAllMatchedEights, getAllMatchedSixs}
import puzzle.objects.Square

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
      val groupsOfFour = PuzzleExecutor.getAllMatchedQuarters(pairs)
      println("groups of 4 size:" + groupsOfFour.size)
      val groupsOfSix = getAllMatchedSixs(groupsOfFour)
      println("groups of 6 size:" + groupsOfSix.size)
      val groupsOfEight = getAllMatchedEights(groupsOfSix)
      println("groups of 8 size:" + groupsOfEight.size)
      val filteredGroups = PuzzleExecutor.filterGroups(groupsOfFour)
      println("filteredGroups size:" + filteredGroups.size)
      val results = PuzzleExecutor.mergeIntoResult(filteredGroups, groupsOfFour)
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
