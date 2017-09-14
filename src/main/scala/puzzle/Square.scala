package puzzle

case class Square(leftUp: Int, rightUp: Int, leftDown: Int, rightDown: Int) {
  def create(value: Int): Square = {
    val leftUp: Int = value / 1000
    val rightUp: Int = (value / 100) % 10
    val leftDown: Int = (value / 10) % 100
    val rightDown: Int = value % 1000
    Square(leftUp, rightUp, leftDown, rightDown)
  }
}
