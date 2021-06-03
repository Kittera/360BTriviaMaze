
import model.Player
import model.TriviaMaze
import javax.swing.JPanel

fun main() {
    Thread.sleep(1000)
    val maze = TriviaMaze(8, 8)
    val room = maze.getRoom(1, 1)
    maze.addPlayer(Player(room))
    maze.revalidate()
    maze.parent.repaint()
}
