
import model.*
import kotlin.system.exitProcess

fun main() {
    val maze = TriviaMaze(20, 20)
    val room1 = maze.getRoom(1, 1)
    val testPlayer = Player(room1)
    maze.addPlayer(testPlayer)
    Thread.sleep(750)

    val seenList = mutableListOf<MazeRoom>(room1)
    for (i in 1 until 30000) {
        var direction = Direction.random()
        var chosenDoor = testPlayer.currentRoom.getDoor(direction)
        var unique = false
        var attempts = 0

        while (!unique) {
            direction = Direction.random()
            chosenDoor = testPlayer.currentRoom.getDoor(direction)
            chosenDoor.ifPresent{ door -> unique = door.roomBehind() !in seenList }
            if (attempts++ > 20) seenList.clear()
        }
        val foundDoor = chosenDoor.get()
        val ques = foundDoor.question
        foundDoor.tryAnswer(ques.correctAnswer)
        maze.movePlayer(direction)
        seenList.add(testPlayer.currentRoom)
        Thread.sleep(2)
    }
    Thread.sleep(500)
    exitProcess(0)

}
