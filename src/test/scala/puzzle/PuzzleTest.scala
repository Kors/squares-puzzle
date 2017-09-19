package puzzle

import org.scalatest.FunSpec

import scala.io.Source

class PuzzleTest extends FunSpec {
  info("Starting...")

  describe("Integration tests") {

    val inputFileName = "src/main/resources/example4.txt"
    val outputFileName = "src/main/resources/test_output.txt"
    val resultToCompare = "src/main/resources/output4.txt"

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

    def read(fileName: String): List[String] =
      Source.fromFile(fileName).getLines.toList

  }

}
