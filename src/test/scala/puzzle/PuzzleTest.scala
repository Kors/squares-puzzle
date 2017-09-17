package puzzle

import org.scalatest.FunSpec
import puzzle.PuzzleExecutor._
import puzzle.objects.Square

import scala.io.Source

class PuzzleTest extends FunSpec {
  info("Starting...")

  val inputFileName = "src/main/resources/example4.txt"
  val outputFileName = "src/main/resources/test_output.txt"
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
      val groupsOfEight = getAllMatchedEights(groupsOfSix, groupsOfFour)
      println("groups of 8 size:" + groupsOfEight.size)
      val groupsOfTen = getAllMatchedTens(groupsOfEight, groupsOfFour)
      println("groups of 10 size:" + groupsOfTen.size)
      val results = getResults(groupsOfTen, groupsOfFour)
      println("results size:" + results.size)
      DataIO.write(results, outputFileName)
      assert(results.nonEmpty)
      val output = read(outputFileName)
      val correct = read(resultToCompare)
      assert(output == correct)
    }
  }

  def read(fileName: String): List[String] =
    Source.fromFile(fileName).getLines.toList


}
