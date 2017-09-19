package puzzle

import org.scalatest.FunSpec
import puzzle.objects.Square

import scala.io.Source

class PuzzleTest extends FunSpec {
  info("Starting...")

  describe("IO tests") {
    it("read from file successfully") {
      val squares = DataIO.read(inputFileName)
      println("squares size:" + squares.size)
      assertResult(12)(squares.size)
    }

    it("write to file successfully") {
      val testFile = "testFile"
      DataIO.write(Seq(Square("1 2 3 4").toFormattedString), testFile)
      val actualResults = read(testFile)
      val expectedResults = List("1 2 3 4", "")
      assertResult(expectedResults)(actualResults)
    }
  }

  describe("Factory tests") {
    it("create square successfully") {
      val square = Square("1 2 3 4")
      assertResult(1)(square.leftUp)
      assertResult(2)(square.rightUp)
      assertResult(3)(square.leftDown)
      assertResult(4)(square.rightDown)
    }

    it("fail to create square (wrong argument numbers)") {
      assertThrows[IllegalArgumentException](Square("12 3 4"))
    }

    it("fail to create square (wrong int value)") {
      assertThrows[IllegalArgumentException](Square("1 2.34 3 4"))
    }
  }

  val inputFileName = "src/main/resources/example4.txt"
  val outputFileName = "src/main/resources/test_output.txt"
  val resultToCompare = "src/main/resources/output4.txt"

  describe("Integration tests") {
    it("full cycle") {
      val squares = DataIO.read(inputFileName)
      val results = PuzzleExecutor.execute(squares)
      println("results size:" + results.size)
      DataIO.write(results, outputFileName)
      assert(results.nonEmpty)
      val actualResults = read(outputFileName)
      val expectedResults = read(resultToCompare)
      assertResult(expectedResults)(actualResults)
    }
  }

  def read(fileName: String): List[String] =
    Source.fromFile(fileName).getLines.toList


}
