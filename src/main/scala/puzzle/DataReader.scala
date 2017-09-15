package puzzle

import scala.io.Source
import java.io.File

class DataReader {

  def read(fileName: String): List[Square] = Source.fromFile(fileName).getLines.map(s => SquareFactory.apply(s)).toList

}
