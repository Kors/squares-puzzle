package puzzle

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import java.io.{File, PrintWriter}

import puzzle.objects.Square

object DataIO {

  def read(fileName: String): List[Square] = {
    val strings = Source.fromFile(fileName).getLines.toList
    val squares: ArrayBuffer[Square] = ArrayBuffer()
    for (i <- 0 to 11)
      squares += Square.apply(strings(i), i)
    squares.toList
  }

  def write(data: List[_], fileName: String): Unit = {
    val writer = new PrintWriter(new File(fileName))
    data.foreach(result => {
      writer.write(result.toString)
      writer.write(System.lineSeparator())
    })
    writer.close()
  }

}
