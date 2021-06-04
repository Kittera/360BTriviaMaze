
import model.*
import kotlin.system.exitProcess
import kotlin.system.measureTimeMillis

fun main() {

    val maze: TriviaMaze
    val time = measureTimeMillis { maze = TriviaMaze(15, 15) }
    println("Maze Generation took $time ms.")
    val room1 = maze.getRoom(1, 1)
    val testPlayer = Player(room1)
    maze.addPlayer(testPlayer)
    Thread.sleep(750)

    val seenList = mutableListOf<MazeRoom>(room1)
    for (i in 1 until 5000) {
        var direction = Direction.random()
        var chosenDoor = testPlayer.currentRoom.getDoor(direction)
        var unique = false
        var attempts = 0

        while (!unique) {
            direction = Direction.random()
            chosenDoor = testPlayer.currentRoom.getDoor(direction)
            chosenDoor.ifPresent{ door -> unique = door.roomBehind() !in seenList }
            if (attempts++ > 90) seenList.clear()
        }
        val foundDoor = chosenDoor.get()
        val ques = foundDoor.question
        foundDoor.tryAnswer(ques.correctAnswer)
        maze.movePlayer(direction)
        seenList.add(testPlayer.currentRoom)
        Thread.sleep(5)
    }
    Thread.sleep(500)
    exitProcess(0)

}
