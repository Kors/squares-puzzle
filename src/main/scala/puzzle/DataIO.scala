package puzzle

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import java.io.{File, PrintWriter}

import puzzle.objects.Square

object DataIO {

  def read(fileName: String): Seq[Square] =
    Source.fromFile(fileName).getLines.map(line => Square(line)).toList

  def write[T](data: Seq[T], fileName: String): Unit = {
    val writer = new PrintWriter(new File(fileName))
    data.foreach(result => {
      writer.write(result.toString)
      writer.write(System.lineSeparator())
    })
    writer.close()
  }

}
