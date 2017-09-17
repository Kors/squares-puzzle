package puzzle

import org.scalatest.FunSpec
import puzzle.PuzzleExecutor.getValues
import puzzle.objects.{EightOfSquares, PairOfSquares, SixOfSquares, SquareOfSquares}

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
      val results = PuzzleExecutor.execute(squares)
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
