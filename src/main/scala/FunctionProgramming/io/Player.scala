package FunctionProgramming.io

case class Player(name: String, score: Int)

object Player {
  def contest(p1: Player, p2: Player): Unit = {
    if (p1.score > p2.score) {
      print(s"${p1.name} is winner")
    } else if (p2.score > p1.score) {
      print(s"${p1.name} is winner")
    } else {
      print("It's a draw")
    }
  }

  def winer(p1: Player, p2: Player): Option[Player] = {
    if (p1.score > p2.score) {
      Some(p1)
    } else if (p1.score < p2.score) {
      Some(p2)
    } else {
      None
    }
  }

  def contest1(p1: Player, p2: Player): Unit = winer(p1, p2) match {
    case Some(player) => print(s"${player.name} is winner")
    case None => print("it's a draw")
  }

  def winnerMessage(p: Option[Player]) = p map {
    case Player(name, _) => s"${name} is winner"
  } getOrElse ("It's a draw")

  def contest2(p1: Player, p2: Player): Unit = println(winnerMessage(winer(p1, p2)))
}
