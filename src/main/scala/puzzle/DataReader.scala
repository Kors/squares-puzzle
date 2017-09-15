package puzzle

import scala.io.Source

object DataReader {

  def read(fileName: String): List[Square] =
    Source.fromFile(fileName).getLines.map(s => SquareFactory.apply(s)).toList

}
