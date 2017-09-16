package puzzle

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import java.io.{File, PrintWriter}

object DataIO {

  def read(fileName: String): List[Square] =
    Source.fromFile(fileName).getLines.map(s => Square.apply(s)).toList

  def write(data: ArrayBuffer[List[Square]], fileName: String): Unit = {
    val writer = new PrintWriter(new File(fileName))
    data.foreach(result => {
      result.foreach(square => writer.write(square.toFormattedString))
      writer.write(System.lineSeparator())
    })
    writer.close()
  }

}
