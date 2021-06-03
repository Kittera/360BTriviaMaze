
import model.Direction
import model.Player
import model.TriviaMaze
import kotlin.system.exitProcess

fun main() {
    val maze = TriviaMaze(5, 5)
    val room1 = maze.getRoom(1, 1)
    val room2 = maze.getRoom(2, 1)
    val testPlayer = Player(room1)
    maze.addPlayer(testPlayer)
    Thread.sleep(500)

    for (i in 1 until 100) {
        var direction = Direction.random()
        var chosenDoor = testPlayer.currentRoom.getDoor(direction)

        while (!chosenDoor.isPresent) {
            direction = Direction.random()
            chosenDoor = testPlayer.currentRoom.getDoor(direction)
        }
        val foundDoor = chosenDoor.get()
        val ques = foundDoor.question
        foundDoor.tryAnswer(ques.correctAnswer)
        maze.movePlayer(direction)
        Thread.sleep(250)
    }
    Thread.sleep(500)
    exitProcess(0)

}
