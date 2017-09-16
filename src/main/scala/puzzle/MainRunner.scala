package puzzle

object MainRunner {

  // could be send as params
  val inputFileName = "src/main/resources/example3.txt"
  val outputFileName = "src/main/resources/output.txt"

  def main(args: Array[String]): Unit = {
    System.out.println("Puzzle processing...")
    val squares = DataIO.read(inputFileName)
    val results = PuzzleExecutor.execute(squares)
    DataIO.write(results, outputFileName)
    System.out.println("processed successfully.")
  }
}
