package puzzle

import org.scalatest.FunSpec
import puzzle.objects.Square

import scala.io.Source

class DataIOTest extends FunSpec {

  describe("IO tests") {

    it("read from file successfully") {
      val inputFileName = "src/main/resources/example4.txt"
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

    def read(fileName: String): List[String] =
      Source.fromFile(fileName).getLines.toList

  }

}
