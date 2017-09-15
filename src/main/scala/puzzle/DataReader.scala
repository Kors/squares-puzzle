package puzzle

import scala.io.Source
import java.io.File

class DataReader {

  def read(fileName: String): List[Square] = {
    println(new File(fileName).getAbsolutePath)
    for (line <- Source.fromFile(fileName).getLines) {
      println(line)
    }
    Source.fromFile(fileName).getLines.map(s => Square.apply(s)).toList
  }

}
