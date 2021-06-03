
import model.Player
import model.TriviaMaze

fun main() {
    val maze = TriviaMaze(15, 15)

    val room = maze.getRoom(1, 1)
    maze.addPlayer(Player(room))

    Thread.sleep(3000)
    room.leave()
}
